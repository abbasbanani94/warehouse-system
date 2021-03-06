package com.who.warehousesystem.service;

import com.who.warehousesystem.dto.ItemDpSaveDto;
import com.who.warehousesystem.model.*;
import com.who.warehousesystem.repository.ItemDpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemDpService {

    @Autowired
    ItemDpRepository itemDpRepository;

    @Autowired
    UserService userService;

    @Autowired
    DistributionPlanService dpService;

    @Autowired
    HealthCenterService healthCenterService;

    @Autowired
    ItemPoService itemPoService;

    @Autowired
    InventoryTypeService inventoryTypeService;

    @Autowired
    ItemInventoryService itemInventoryService;

    @Autowired
    ItemWbService itemWbService;

    public List<ItemDp> findItemDpsByItemPo(Integer itemPoId) {
        return itemDpRepository.findItemDpsByItemPo(itemPoId).orElse(new ArrayList<>());
    }

    public boolean findItemDpByHealthCenter(Integer healthCenterId) {
        List<ItemDp> itemDps = itemDpRepository.findItemDpByHealthCenter(healthCenterId);
        if(itemDps.size() >= 1)
            return true;
        else
            return false;
    }

    public ItemDp saveItemDp(ItemDpSaveDto dto, Integer userId) throws Exception {
        checkItemDpDuplicate(dto);
        User user = userService.findUserById(userId);
        DistributionPlan dp = dpService.findDpByDetails(dto.getPlanId(),dto.getEnName(),dto.getArName(),dto.getPlanDate(),user);
        HealthCenter center = healthCenterService.findHealthCenterById(dto.getCenterId());
        ItemPo itemPo = itemPoService.findItemPoById(dto.getItemPoId());
        ItemDp itemDp = new ItemDp(dp,center,itemPo,dto.getQty(),user);

        itemDp = itemDpRepository.save(itemDp);

        InventoryType inventoryType = inventoryTypeService.findTypeById(2); //Out
        ItemInventory itemInventory = new ItemInventory(itemDp,inventoryType,user);

        itemInventoryService.saveItemInventory(itemInventory);
        itemPoService.subInventoryByItemDp(itemDp,user);

        return itemDp;
    }

    private void checkItemDpDuplicate(ItemDpSaveDto dto) throws Exception {
        if(itemDpRepository.findItemDpByDpAndCenterAndItemPo(dto.getPlanId(),dto.getCenterId(),dto.getItemPoId()) != null)
            throw new Exception("This itemPo already saved for this center and DP");
    }

    public List<ItemDp> findAllItemDpDgv() {
        return itemDpRepository.findAllItemDpDgv().orElse(new ArrayList<>());
    }

    public ItemDp editItemDp(Integer id, ItemDpSaveDto dto, Integer userId) throws Exception {
        checkItemDpDuplicate(id,dto.getPlanId(),dto.getCenterId(),dto.getItemPoId());
        User user = userService.findUserById(userId);
        ItemDp itemDp = findItemDpById(id);
        itemPoService.addInventoryByItemDp(itemDp, user);
        InventoryType inventoryType = inventoryTypeService.findTypeById(2); //Out
        ItemInventory itemInventory = itemInventoryService.findItemInventoryByTypeAndItemDp
                (inventoryType.getId(),itemDp.getId());
        ItemPo itemPo = itemPoService.findItemPoById(dto.getItemPoId());
        DistributionPlan dp = dpService.findDpByDetails(dto.getPlanId(),dto.getEnName(),dto.getArName(),dto.getPlanDate(),user);
        HealthCenter healthCenter = healthCenterService.findHealthCenterById(dto.getCenterId());
        itemDp.setDistributionPlan(dp);
        itemDp.setHealthCenter(healthCenter);
        itemDp.setItemPo(itemPo);
        itemDp.setUpdatedBy(user);
        itemDp.setQty(dto.getQty());
        itemDp = itemDpRepository.save(itemDp);

        itemInventory.setItemPo(itemPo);
        itemInventory.setItemDp(itemDp);
        itemInventory.setOutQty(dto.getQty());
        itemInventory.setUpdatedBy(user);
        itemInventory.setNote("ItemDp Id: " + itemDp.getId() + ", Plan Name: " + itemDp.getDistributionPlan().getEnName() +
                ", Center: " + itemDp.getHealthCenter().getEnName() + " - " + itemDp.getHealthCenter().getDistrict().getEnName() +
                " - " + itemDp.getHealthCenter().getDistrict().getCity().getEnName() + ", Out Qty: " + itemDp.getQty());
        itemInventoryService.saveItemInventory(itemInventory);
        itemPoService.subInventoryByItemDp(itemDp, user);
        return itemDp;
    }

    private void checkItemDpDuplicate(Integer id, Integer planId, Integer centerId, Integer itemPoId) throws Exception {
        ItemDp itemDp = itemDpRepository.findItemDpByDpAndCenterAndItemPo(planId,centerId,itemPoId);
        if(itemDp != null && itemDp.getId() != id)
            throw new Exception("This itemPo already saved for this center and DP");
    }

    public void deleteItemDp(Integer id, Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        ItemDp itemDp = findItemDpById(id);
        checkItemDpExistence(itemDp.getId());
        itemDp.setUpdatedBy(user);
        itemDp.setActive(false);
        itemPoService.addInventoryByItemDp(itemDp, user);
        itemDpRepository.save(itemDp);
        ItemInventory itemInventory = itemInventoryService.findItemInventoryByTypeAndItemDp(2,id);
        itemInventory.setActive(false);
        itemInventory.setUpdatedBy(user);
        itemInventoryService.saveItemInventory(itemInventory);
    }

    private void checkItemDpExistence(Integer id) throws Exception {
        if(itemWbService.findItemWbByItemDp(id) != null)
            throw new Exception("Item DP cannot be deleted because it's included in other tables");
    }

    private ItemDp findItemDpById(Integer id) throws Exception {
        return itemDpRepository.findItemDpById(id).orElseThrow(() ->
                new Exception("No Item DP found for ID : " + id));
    }

    @Autowired
    EntityManager entityManager;

    public List<ItemDp> searchItemDp(String planId, String date, String poId, String itemPoId, String cityId,
                                     String districtId, String centerId, String qty,boolean d) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ItemDp> cq = cb.createQuery(ItemDp.class);
        Root<ItemDp> root = cq.from(ItemDp.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(root.get("active"), true));

        if(!planId.isBlank() && !planId.isEmpty())
            predicates.add(cb.equal(root.join("distributionPlan").get("id"), planId));
        if(d)
            predicates.add(cb.equal(root.join("distributionPlan").get("dDate"), LocalDate.parse(date)));
        if(!poId.isBlank() && !poId.isEmpty())
            predicates.add(cb.equal(root.join("itemPo").join("purchaseOrder").get("id"), poId));
        if(!itemPoId.isBlank() && !itemPoId.isEmpty())
            predicates.add(cb.equal(root.join("itemPo").get("id"), itemPoId));
        if(!cityId.isBlank() && !cityId.isEmpty())
            predicates.add(cb.equal(root.join("healthCenter").join("district").join("city").get("id"), cityId));
        if(!districtId.isBlank() && !districtId.isEmpty())
            predicates.add(cb.equal(root.join("healthCenter").join("district").get("id"), districtId));
        if(!centerId.isBlank() && !centerId.isEmpty())
            predicates.add(cb.equal(root.join("healthCenter").get("id"), centerId));
        if(!qty.isBlank() && !qty.isEmpty())
            predicates.add(cb.equal(root.get("qty"), qty));

        cq.select(root).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        return entityManager.createQuery(cq).getResultList();
    }

    public List<String> findDpItemsByCenterNoWb(Integer centerId) {
        return itemDpRepository.findItemDpByCenterNoWb(centerId).orElse(new ArrayList<>()).stream().map(itemDp -> {
            return "I" + itemDp.getId() + " - " + itemDp.getItemPo().getItem().getName() + " - PO#" +
                    itemDp.getItemPo().getPurchaseOrder().getNo() + " - Qty : " + itemDp.getQty();
        }).collect(Collectors.toList());
    }

    public List<String> findDpItemsByCenterWb(Integer centerId) {
        return itemDpRepository.findItemDpByCenterWb(centerId).orElse(new ArrayList<>()).stream().map(itemDp -> {
            return "I" + itemDp.getId() + " - " + itemDp.getItemPo().getItem().getName() + " - Qty : " +
                    itemDp.getQty();
        }).collect(Collectors.toList());
    }

    public List<ItemDp> extractItemDpsFromWbStringList(List<String> wbList) throws Exception {
        List<ItemDp> itemDps = new ArrayList<>();
        for(String item : wbList) {
            if(item.startsWith("I")) {
                Integer id = Integer.parseInt(item.substring(1,item.indexOf(' ')));
                ItemDp itemDp = findItemDpById(id);
                itemDps.add(itemDp);
            }
        }
        return itemDps;
    }

    public List<ItemDp> findItemDpByDp(Integer dpId) {
        return itemDpRepository.findItemDpByDp(dpId).orElse(new ArrayList<>());
    }
}
