package com.who.warehousesystem.service;

import com.who.warehousesystem.dto.KitDpSaveDto;
import com.who.warehousesystem.model.*;
import com.who.warehousesystem.repository.KitDpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KitDpService {

    @Autowired
    KitDpRepository kitDpRepository;

    @Autowired
    UserService userService;

    @Autowired
    DistributionPlanService dpService;

    @Autowired
    HealthCenterService healthCenterService;

    @Autowired
    KitPoService kitPoService;

    @Autowired
    InventoryTypeService inventoryTypeService;

    @Autowired
    KitInventoryService kitInventoryService;

    @Autowired
    KitWbService kitWbService;

    public KitDp findKitDpByKitPo(Integer kitPoId) {
        return kitDpRepository.findKitDpByKitPo(kitPoId);
    }

    public boolean findKitDpByHealthCenter(Integer healthCenterId) {
        List<KitDp> kitDps = kitDpRepository.findKitDpByHealthCenter(healthCenterId);
        if(kitDps.size() >= 1)
            return true;
        else
            return false;
    }

    public List<KitDp> findAllKitDpDgv() {
        return kitDpRepository.findAllKitDpDgv().orElse(new ArrayList<>());
    }

    public KitDp saveKitDp(KitDpSaveDto dto, Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        DistributionPlan dp = dpService.findDpByDetails(dto.getPlanId(),dto.getEnName(),dto.getArName(),dto.getPlanDate(),user);
        HealthCenter center = healthCenterService.findHealthCenterById(dto.getCenterId());
        KitPo kitPo = kitPoService.findKitPoById(dto.getKitPoId());

        KitDp kitDp = new KitDp(dp,center,kitPo,dto.getQty(),user);
        kitDp = kitDpRepository.save(kitDp);

        InventoryType inventoryType = inventoryTypeService.findTypeById(2); //Out
        KitInventory kitInventory = new KitInventory(kitDp, inventoryType, user);

        kitInventoryService.saveKitInventory(kitInventory);
        kitPoService.subInventoryByKitDp(kitDp, user);

        return kitDp;
    }
}
