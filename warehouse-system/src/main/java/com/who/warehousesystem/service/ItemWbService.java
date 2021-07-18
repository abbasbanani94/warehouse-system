package com.who.warehousesystem.service;

import com.who.warehousesystem.model.ItemWb;
import com.who.warehousesystem.repository.ItemWbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemWbService {

    @Autowired
    ItemWbRepository itemWbRepository;

    public ItemWb findItemWbByItemDp(Integer itemDpId) {
        return itemWbRepository.findItemWbByItemDp(itemDpId);
    }
}
