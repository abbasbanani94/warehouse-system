package com.who.warehousesystem.service;

import com.who.warehousesystem.model.ItemWb;
import com.who.warehousesystem.repository.ItemWbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemWbService {

    @Autowired
    ItemWbRepository itemWbRepository;

    public ItemWb findItemWbByItemDp(Integer itemDpId) {
        return itemWbRepository.findItemWbByItemDp(itemDpId);
    }

    public List<ItemWb> findItemWbByWb(Integer wbId) {
        return itemWbRepository.findItemWbByWb(wbId).orElse(new ArrayList<>());
    }
}
