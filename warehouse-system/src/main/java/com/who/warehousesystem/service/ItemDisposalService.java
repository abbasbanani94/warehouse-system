package com.who.warehousesystem.service;

import com.who.warehousesystem.model.ItemDisposal;
import com.who.warehousesystem.repository.ItemDisposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemDisposalService {

    @Autowired
    ItemDisposalRepository itemDisposalRepository;

    public ItemDisposal findItemDisposalByItemPo(Integer itemPoId) {
        return itemDisposalRepository.findItemDisposalByItemPo(itemPoId);
    }
}
