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

    public KitDp editKitDp(Integer id, KitDpSaveDto dto, Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        KitDp kitDp = findKitDpById(id);
        kitPoService.addInventoryByKitDp(kitDp, user);
        InventoryType inventoryType = inventoryTypeService.findTypeById(2); //Out
        KitInventory kitInventory = kitInventoryService.findKitInventoryByTypeAndKitDp
                (inventoryType.getId(), id);
        KitPo kitPo = kitPoService.findKitPoById(dto.getKitPoId());
        DistributionPlan dp = dpService.findDpByDetails(dto.getPlanId(),dto.getEnName(),dto.getArName(),dto.getPlanDate(),user);
        HealthCenter center = healthCenterService.findHealthCenterById(dto.getCenterId());
        kitDp.setDistributionPlan(dp);
        kitDp.setHealthCenter(center);
        kitDp.setKitPo(kitPo);
        kitDp.setQty(dto.getQty());
        kitDp.setUpdatedBy(user);
        kitDp = kitDpRepository.save(kitDp);

        kitInventory.setKitPo(kitPo);
        kitInventory.setKitDp(kitDp);
        kitInventory.setOutQty(kitDp.getQty());
        kitInventory.setUpdatedBy(user);
        kitInventory.setNote("KitDp ID: " + id + ", Plan Name: " + kitDp.getDistributionPlan().getEnName() + ", " +
                "Kit: " + kitDp.getKitPo().getKit().getName() + ", Out Qty: " + kitDp.getQty());

        kitInventoryService.saveKitInventory(kitInventory);
        kitPoService.subInventoryByKitDp(kitDp, user);
        return kitDp;
    }

    private KitDp findKitDpById(Integer id) throws Exception {
        return kitDpRepository.findKitDpById(id).orElseThrow(() ->
                new Exception("No Kit DP found for ID : " + id));
    }
}
