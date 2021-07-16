package com.who.warehousesystem.service;

import com.who.warehousesystem.model.ItemInventory;
import com.who.warehousesystem.model.User;
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
}
