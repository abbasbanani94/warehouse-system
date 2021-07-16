package com.who.warehousesystem.service;

import com.who.warehousesystem.model.ItemDp;
import com.who.warehousesystem.repository.ItemDpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemDpService {

    @Autowired
    ItemDpRepository itemDpRepository;

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
}
