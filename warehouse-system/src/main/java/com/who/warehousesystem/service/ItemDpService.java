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
        itemPoService.editInventoryByItemDp(itemDp,user);

        return itemDp;
    }
}
