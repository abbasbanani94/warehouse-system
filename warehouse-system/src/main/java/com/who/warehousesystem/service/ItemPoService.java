package com.who.warehousesystem.service;

import com.who.warehousesystem.dto.ItemPoSaveDto;
import com.who.warehousesystem.dto.ItemPoSearchDto;
import com.who.warehousesystem.model.*;
import com.who.warehousesystem.repository.ItemPoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemPoService {

    @Autowired
    ItemPoRepository itemPoRepository;

    @Autowired
    PurchaseOrderService purchaseOrderService;

    @Autowired
    ItemService itemService;

    @Autowired
    UserService userService;

    @Autowired
    CountryService countryService;

    @Autowired
    ItemInventoryService itemInventoryService;

    @Autowired
    InventoryTypeService inventoryTypeService;

    @Autowired
    EntityManager entityManager;

    @Autowired
    ItemDisposalService itemDisposalService;

    @Autowired
    ItemDpService itemDpService;

    @Autowired
    ItemPoCheckingService itemPoCheckingService;

    public List<ItemPo> findItemPoDgv() {
        return itemPoRepository.findItemPoDgv().orElse(new ArrayList<>());
    }

    private ItemPo initiateObject (ItemPoSaveDto dto, User user) throws Exception {
        PurchaseOrder po = purchaseOrderService.findPurchaseOrderByPoNo(dto.getPoId(),dto.getPoNo(),
                user);
        Item item = itemService.findItemByItemPo(dto, user);
        Country country = countryService.findCountryByName(dto.getCountry(),user);
        return new ItemPo(item,po,dto.getDateReceived(),dto.getManDate(),dto.getExpDate(),country,
                dto.getBatchNo(),dto.getPackaging(),dto.getPalletsQty(),dto.getBoxesPerPallet(),dto.getPacksPerBox(),
                dto.getPiecesPerPack(),dto.getTotalQty(),user, dto.getLocation());
    }

    public ItemPo saveItemPo(ItemPoSaveDto dto, Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        ItemPo itemPo = initiateObject(dto, user);
        itemPo = itemPoRepository.save(itemPo);
        InventoryType inventoryType = inventoryTypeService.findTypeById(1);//In
        ItemInventory itemInventory = new ItemInventory(itemPo,user,inventoryType);
        itemInventoryService.saveItemInventory(itemInventory);
        return itemPo;
    }

    public ItemPo editItemPo(Integer id, ItemPoSaveDto dto, Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        ItemPo itemPo = initiateObject(dto, user);
        itemPo.setId(id);
        itemPo.setActive(true);
        itemPo.setUpdatedBy(user);
        itemPo = itemPoRepository.save(itemPo);
        InventoryType inventoryType = inventoryTypeService.findTypeById(1);//In
        ItemInventory itemInventory = itemInventoryService.findItemInventoryByTypeAndItemPo
                (inventoryType.getId(),itemPo.getId());
        itemInventory.setInQty(itemPo.getTotalQty());
        itemInventory.setNote("ItemPo Id: " + itemPo.getId() + ", PO No: " + itemPo.getPurchaseOrder().getNo() + "," +
                "Item: " + itemPo.getItem().getName() + ", Exp. Date: " + itemPo.getExpDate() +
                ", In Qty: " + itemPo.getTotalQty());
        itemInventory.setUpdatedBy(user);
        itemInventoryService.saveItemInventory(itemInventory);
        return itemPo;
    }

    public void deleteItemPo(Integer id, Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        ItemPo itemPo = itemPoRepository.findItemPoById(id).orElseThrow(() -> new Exception("Item PO not found for ID : " + id));
        checkItemPoExistence(id);
        itemPo.setActive(false);
        itemPo.setUpdatedBy(user);
        itemPoRepository.save(itemPo);
        ItemInventory itemInventory = itemInventoryService.findItemInventoryByTypeAndItemPo
                (1,itemPo.getId());
        itemInventory.setActive(false);
        itemInventory.setUpdatedBy(user);
        itemInventoryService.saveItemInventory(itemInventory);
    }

    private void checkItemPoExistence(Integer itemPoId) throws Exception {
        if(itemDisposalService.findItemDisposalByItemPo(itemPoId) != null ||
           itemDpService.findItemDpByItemPo(itemPoId) != null ||
           itemPoCheckingService.findItemCheckingByItemPo(itemPoId) != null)
            throw new Exception("ItemPo ID : " + itemPoId + " cannot be deleted because it's included with another tables");
    }

    public List<ItemPo> searchItemPo(ItemPoSearchDto dto) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ItemPo> criteriaQuery = criteriaBuilder.createQuery(ItemPo.class);
        Root<ItemPo> root = criteriaQuery.from(ItemPo.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(criteriaBuilder.equal(root.get("active"),true));

        if(!dto.getBatch().isEmpty() && !dto.getBatch().isBlank())
            predicates.add(criteriaBuilder.like(root.get("batchNo"), "%" + dto.getBatch() + "%"));
        if(!dto.getBoxes().isEmpty() && !dto.getBoxes().isBlank())
            predicates.add(criteriaBuilder.equal(root.get("boxesPerPallet"), dto.getBoxes()));
        if(!dto.getCountry().isEmpty() && !dto.getCountry().isBlank())
            predicates.add(criteriaBuilder.equal(root.join("country").get("name"),dto.getCountry()));
        if(!dto.getDateReceived().isEmpty() && dto.getDateReceived().isBlank())
            predicates.add(criteriaBuilder.equal(root.get("recDate"), dto.getDateReceived()));
        if(!dto.getDescription().isEmpty() && !dto.getDescription().isBlank())
            predicates.add(criteriaBuilder.like(root.join("item").get("description"),dto.getDescription()));
        if(!dto.getExpDate().isEmpty() && !dto.getExpDate().isBlank())
            predicates.add(criteriaBuilder.equal(root.get("expDate"), dto.getExpDate()));
        if(!dto.getItemId().isEmpty() && !dto.getItemId().isBlank())
            predicates.add(criteriaBuilder.equal(root.join("item").get("id"), dto.getItemId()));
        if(!dto.getLocation().isEmpty() && !dto.getLocation().isBlank())
            predicates.add(criteriaBuilder.like(root.get("location"), "%" + dto.getLocation() + "%"));
        if(!dto.getManDate().isEmpty() && !dto.getManDate().isBlank())
            predicates.add(criteriaBuilder.equal(root.get("manDate"), dto.getManDate()));
        if(!dto.getMaxTemp().isEmpty() && !dto.getMaxTemp().isBlank())
            predicates.add(criteriaBuilder.equal(root.join("item").get("maxTemp"), dto.getMaxTemp()));
        if(!dto.getMinTemp().isEmpty() && !dto.getMinTemp().isBlank())
            predicates.add(criteriaBuilder.equal(root.join("item").get("minTemp"), dto.getMinTemp()));
        if(!dto.getPackaging().isEmpty() && !dto.getPackaging().isBlank())
            predicates.add(criteriaBuilder.like(root.get("packaging"), "%" + dto.getPackaging() + "%"));
        if(!dto.getPacks().isEmpty() && !dto.getPacks().isBlank())
            predicates.add(criteriaBuilder.equal(root.get("packsPerBox"), dto.getPacks()));
        if(!dto.getPallets().isEmpty() && !dto.getPallets().isBlank())
            predicates.add(criteriaBuilder.equal(root.get("palletsQty"), dto.getPallets()));
        if(!dto.getPoId().isEmpty() && !dto.getPoId().isBlank())
            predicates.add(criteriaBuilder.equal(root.join("purchaseOrder").get("id"), dto.getPoId()));
        if(!dto.getTotalQty().isEmpty() && !dto.getTotalQty().isBlank())
            predicates.add(criteriaBuilder.equal(root.get("totalQty"),dto.getTotalQty()));

        criteriaQuery.select(root).where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public List<ItemPo> findItemsPoComboByPo(Integer poId) throws Exception {
        return itemPoRepository.findItemsPoByPo(poId).orElseThrow(() ->
                new Exception("No Items added with PO ID : " + poId));
    }

    public ItemPo findItemPoById(Integer id) throws Exception {
        return itemPoRepository.findItemPoById(id).orElseThrow(() ->
                new Exception("No Item PO with ID : " + id));
    }

    private void editInventoryByItemDp (ItemDp itemDp,User user,String operation) {
        ItemPo itemPo = itemDp.getItemPo();
        if(operation.equalsIgnoreCase("add"))
            itemPo.setInventory(itemPo.getInventory() + itemDp.getQty());
        else
            itemPo.setInventory(itemPo.getInventory() - itemDp.getQty());
        itemPo.setUpdatedBy(user);
        itemPoRepository.save(itemPo);
    }

    public void subInventoryByItemDp(ItemDp itemDp, User user) {
        editInventoryByItemDp(itemDp, user, "sub");
    }

    public void addInventoryByItemDp(ItemDp itemDp, User user) {
        editInventoryByItemDp(itemDp, user, "add");
    }
}
