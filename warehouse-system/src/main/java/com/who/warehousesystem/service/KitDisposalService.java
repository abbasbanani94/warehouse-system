package com.who.warehousesystem.service;

import com.who.warehousesystem.model.KitDisposal;
import com.who.warehousesystem.repository.KitDisposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KitDisposalService {

    @Autowired
    KitDisposalRepository kitDisposalRepository;

    public KitDisposal findKitDetailByKitPo(Integer kitPoId) {
        return kitDisposalRepository.findKitDisposalByKitPo(kitPoId);
    }
}
