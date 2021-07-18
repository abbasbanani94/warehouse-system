package com.who.warehousesystem.service;

import com.who.warehousesystem.dto.ItemDpSaveDto;
import com.who.warehousesystem.model.*;
import com.who.warehousesystem.repository.ItemDpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public ItemDp findItemDpByItemPo(Integer itemPoId) {
        return itemDpRepository.findItemDpByItemPo(itemPoId);
    }

    public boolean findItemDpByHealthCenter(Integer healthCenterId) {
        List<ItemDp> itemDps = itemDpRepository.findItemDpByHealthCenter(healthCenterId);
        if(itemDps.size() >= 1)
            return true;
        else
            return false;
    }

    public List<ItemDp> findAllItemDp() {
        return itemDpRepository.findAllItemDp().orElse(new ArrayList<>());
    }

    public ItemDp saveItemDp(ItemDpSaveDto dto, Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        DistributionPlan dp = dpService.findDpByItemDp(dto,user);
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

    public List<ItemDp> findAllItemDpDgv() {
        return itemDpRepository.findAllItemDpDgv().orElse(new ArrayList<>());
    }

    public ItemDp editItemDp(Integer id, ItemDpSaveDto dto, Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        ItemDp itemDp = itemDpRepository.findItemDpById(id).orElseThrow(() ->
                new Exception("No Item DP found for ID : " + id));
        itemPoService.addInventoryByItemDp(itemDp, user);
        InventoryType inventoryType = inventoryTypeService.findTypeById(2); //Out
        ItemInventory itemInventory = itemInventoryService.findItemInventoryByTypeAndItemDp
                (inventoryType.getId(),itemDp.getId());
        ItemPo itemPo = itemPoService.findItemPoById(dto.getItemPoId());
        DistributionPlan dp = dpService.findDpByItemDp(dto, user);
        HealthCenter healthCenter = healthCenterService.findHealthCenterById(dto.getCenterId());
        itemDp.setDistributionPlan(dp);
        itemDp.setHealthCenter(healthCenter);
        itemDp.setItemPo(itemPo);
        itemDp.setUpdatedBy(user);
        itemDp = itemDpRepository.save(itemDp);

        itemInventory.setItemPo(itemPo);
        itemInventory.setItemDp(itemDp);
        itemInventory.setOutQty(dto.getQty());
        itemInventory.setUpdatedBy(user);
        itemInventory.setNote("ItemDp Id: " + itemDp.getId() + ", Plan Name: " + itemDp.getDistributionPlan().getEnName() +
                ", Item: " + itemDp.getItemPo().getItem().getName() + ", Out Qty: " + itemDp.getQty());
        itemInventoryService.saveItemInventory(itemInventory);
        itemPoService.subInventoryByItemDp(itemDp, user);
        return itemDp;
    }
}
