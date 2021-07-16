package com.who.warehousesystem.service;

import com.who.warehousesystem.model.KitType;
import com.who.warehousesystem.repository.KitTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KitTypeService {

    @Autowired
    KitTypeRepository kitTypeRepository;

    public KitType findKitTypeByName(String kitType) throws Exception {
        return kitTypeRepository.findKitTypeByName(kitType).orElseThrow(() ->
                new Exception("Kit Type not found for name : " + kitType));
    }
}
