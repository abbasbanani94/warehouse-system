package com.who.warehousesystem.service;

import com.who.warehousesystem.model.KitInventory;
import com.who.warehousesystem.repository.KitInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KitInventoryService {

    @Autowired
    KitInventoryRepository kitInventoryRepository;

    public void saveKitInventory(KitInventory kitInventory) {
        kitInventoryRepository.save(kitInventory);
    }

    public KitInventory findKitInventoryByTypeAndKitPo(Integer typeId, Integer kitPoId) throws Exception {
        return kitInventoryRepository.findKitInventoryByTypeAndKitPo(typeId,kitPoId)
                .orElseThrow(() -> new Exception("No Kit Inventory for PO ID : " + kitPoId + " and Type ID : " +
                        typeId));
    }

    public KitInventory findKitInventoryByTypeAndKitDp(Integer typeId, Integer kitDpId) throws Exception {
        return kitInventoryRepository.findKitInventoryByTypeAndKitDp(typeId, kitDpId)
                .orElseThrow(() -> new Exception("No Kit inventory for Kit DP id : " + kitDpId + " and " +
                        "type ID : " + typeId));
    }

    public KitInventory findKitInventoryByTypeAndKitDisposal(Integer typeId, Integer kitDisposalId) throws Exception {
        return kitInventoryRepository.findKitInventoryByTypeAndKitDisposal(typeId,kitDisposalId)
                .orElseThrow(() -> new Exception("No Kit inventory for Kit Disposal id : " + kitDisposalId + " and " +
                        "type ID : " + typeId));
    }

    public List<KitInventory> findAllKitInventoriesByKitPo(Integer kitPoId) {
        return kitInventoryRepository.findAllKitInventoriesByKitPo(kitPoId).orElse(new ArrayList<>());
    }
}
