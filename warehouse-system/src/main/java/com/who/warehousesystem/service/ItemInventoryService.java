package com.who.warehousesystem.service;

import com.who.warehousesystem.model.ItemInventory;
import com.who.warehousesystem.repository.ItemInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemInventoryService {

    @Autowired
    ItemInventoryRepository itemInventoryRepository;

    public void saveItemInventory(ItemInventory itemInventory) {
        itemInventoryRepository.save(itemInventory);
    }

    public ItemInventory findItemInventoryByTypeAndItemPo(Integer typeId, Integer itemPoId) throws Exception {
        return itemInventoryRepository.findItemInventoryByTypeAndItemPo(typeId, itemPoId)
                .orElseThrow(() -> new Exception("No Item inventory for PO ID : " + itemPoId + " and Type ID : " + typeId));
    }

    public ItemInventory findItemInventoryByTypeAndItemDp(Integer typeId, Integer itemDpId)
            throws Exception {
        return itemInventoryRepository.findItemInventoryByTypeAndItemDp(typeId,itemDpId)
                .orElseThrow(() -> new Exception("No Item inventory for Item DP id : " + itemDpId + " and " +
                        "type ID : " + typeId));
    }

    public ItemInventory findItemInventoryByTypeAndItemDisposal(Integer typeId, Integer itemDisposalId)
            throws Exception {
        return itemInventoryRepository.findItemInventoryByTypeAndItemDisposal(typeId, itemDisposalId)
                .orElseThrow(() -> new Exception("No Item inventory for Item Disposal ID : " + itemDisposalId +
                        " and type ID : " + typeId));
    }
}
