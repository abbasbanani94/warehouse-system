package com.who.warehousesystem.service;

import com.who.warehousesystem.model.ItemPoChecking;
import com.who.warehousesystem.repository.ItemPoCheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemPoCheckingService {

    @Autowired
    ItemPoCheckingRepository itemPoCheckingRepository;

    public ItemPoChecking findItemCheckingByItemPo(Integer itemPoId) {
        return itemPoCheckingRepository.findItemPoCheckingByItemPo(itemPoId);
    }
}
