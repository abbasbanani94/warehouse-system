package com.who.warehousesystem.service;

import com.who.warehousesystem.model.InventoryType;
import com.who.warehousesystem.repository.InventoryTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryTypeService {

    @Autowired
    InventoryTypeRepository inventoryTypeRepository;

    public InventoryType findTypeById(Integer id) throws Exception {
        return inventoryTypeRepository.findTypeById(id).orElseThrow(() -> new Exception("Inventory type not found " +
                "for ID : " + id));
    }
}
