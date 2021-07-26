package com.who.warehousesystem.service;

import com.who.warehousesystem.dto.ItemDisposalSaveDto;
import com.who.warehousesystem.model.*;
import com.who.warehousesystem.repository.ItemDisposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ItemDisposalService {

    @Autowired
    ItemDisposalRepository itemDisposalRepository;

    @Autowired
    UserService userService;

    @Autowired
    ItemPoService itemPoService;

    @Autowired
    ItemInventoryService itemInventoryService;

    @Autowired
    InventoryTypeService inventoryTypeService;

    @Autowired
    DisposalService disposalService;

    public ItemDisposal findItemDisposalByItemPo(Integer itemPoId) {
        return itemDisposalRepository.findItemDisposalByItemPo(itemPoId);
    }

    public List<ItemDisposal> findAllItemDisposalsByDisposal(Integer disposalId) {
        return itemDisposalRepository.findItemDisposalsByDisposal(disposalId).orElse(new ArrayList<>());
    }

    public ItemDisposal saveItemDisposal(Integer disposalId, ItemDisposalSaveDto dto, Integer userId) throws Exception {
        checkDuplicate(disposalId,dto.getItemPoId());
        User user = userService.findUserById(userId);
        Disposal disposal = disposalService.findDisposalById(disposalId);
        ItemPo itemPo = itemPoService.findItemPoById(dto.getItemPoId());
        ItemDisposal itemDisposal = new ItemDisposal(disposal,itemPo,dto.getQty(),user);

        itemDisposal = itemDisposalRepository.save(itemDisposal);
        itemPoService.subInventoryByItemDisposal(itemDisposal,user);
        InventoryType inventoryType = inventoryTypeService.findTypeById(2);//Out

        ItemInventory itemInventory = new ItemInventory(itemDisposal,inventoryType,user);
        itemInventoryService.saveItemInventory(itemInventory);
        return itemDisposal;
    }

    private void checkDuplicate(Integer disposalId, Integer itemPoId) throws Exception {
        if(itemDisposalRepository.findItemDisposalByDisposalAndItemPo(disposalId,itemPoId) != null)
            throwDuplicateException();
    }

    private void throwDuplicateException() throws Exception {
        throw new Exception("the same item was already inserted to this Disposal");
    }

    public ItemDisposal editItemDisposal(Integer id, Integer disposalId, ItemDisposalSaveDto dto, Integer userId)
            throws Exception {
        checkDuplicate(disposalId,dto.getItemPoId(),id);
        User user = userService.findUserById(userId);
        ItemDisposal itemDisposal = findItemDisposalById(id);
        itemPoService.addInventoryByItemDisposal(itemDisposal,user);
        InventoryType inventoryType = inventoryTypeService.findTypeById(2);//Out
        ItemInventory itemInventory = itemInventoryService.findItemInventoryByTypeAndItemDisposal(inventoryType.getId(),
                itemDisposal.getId());
        Disposal disposal = disposalService.findDisposalById(disposalId);
        ItemPo itemPo = itemPoService.findItemPoById(dto.getItemPoId());
        itemDisposal.setDisposal(disposal);
        itemDisposal.setItemPo(itemPo);
        itemDisposal.setQty(dto.getQty());
        itemDisposal.setUpdatedBy(user);
        itemDisposal = itemDisposalRepository.save(itemDisposal);

        itemInventory.setItemPo(itemPo);
        itemInventory.setItemDisposal(itemDisposal);
        itemInventory.setOutQty(itemDisposal.getQty());
        itemInventory.setUpdatedBy(user);
        itemInventory.setNote("ItemDisposal ID: " + itemDisposal.getId() + ", Disposal Reason: " +
                itemDisposal.getDisposal().getReason() + ", Item: " + itemDisposal.getItemPo().getItem().getName() +
                ", Out Qty: " + itemDisposal.getQty());
        itemInventoryService.saveItemInventory(itemInventory);
        itemPoService.subInventoryByItemDisposal(itemDisposal,user);
        return itemDisposal;
    }

    private ItemDisposal findItemDisposalById(Integer id) throws Exception {
        return itemDisposalRepository.findItemDisposalById(id).orElseThrow(() ->
                new Exception("No Item Disposal for ID : " + id));
    }

    private void checkDuplicate(Integer disposalId, Integer itemPoId, Integer id) throws Exception {
        ItemDisposal itemDisposal = itemDisposalRepository.findItemDisposalByDisposalAndItemPo(disposalId,itemPoId);
        if(itemDisposal != null && itemDisposal.getId() != id)
            throwDuplicateException();
    }

    public void deleteItemDisposal(Integer id,Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        ItemDisposal itemDisposal = findItemDisposalById(id);
        itemPoService.addInventoryByItemDisposal(itemDisposal,user);
        ItemInventory itemInventory = itemInventoryService.findItemInventoryByTypeAndItemDisposal(2,id);
        itemDisposal.setActive(false);
        itemDisposal.setUpdatedBy(user);
        itemDisposalRepository.save(itemDisposal);
        itemInventory.setActive(false);
        itemInventory.setUpdatedBy(user);
        itemInventoryService.saveItemInventory(itemInventory);
    }

    @Autowired
    EntityManager entityManager;

    public List<ItemDisposal> searchItemDisposals(Integer disposalId, String poId, String itemPoId, String qty) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ItemDisposal> cq = cb.createQuery(ItemDisposal.class);
        Root<ItemDisposal> root = cq.from(ItemDisposal.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(root.get("active"),true));
        predicates.add(cb.equal(root.join("disposal").get("id"),disposalId));

        if(!poId.isBlank() && !poId.isEmpty())
            predicates.add(cb.equal(root.join("itemPo").join("purchaseOrder").get("id"),poId));
        if(!itemPoId.isBlank() && !itemPoId.isEmpty())
            predicates.add(cb.equal(root.join("itemPo").get("id"), itemPoId));
        if(!qty.isEmpty() && !qty.isBlank())
            predicates.add(cb.equal(root.get("qty"),qty));

        cq.select(root).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        return entityManager.createQuery(cq).getResultList();
    }
}
