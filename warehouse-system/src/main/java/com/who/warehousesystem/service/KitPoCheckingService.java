package com.who.warehousesystem.service;

import com.who.warehousesystem.model.KitPoChecking;
import com.who.warehousesystem.repository.KitPoCheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KitPoCheckingService {

    @Autowired
    KitPoCheckingRepository kitPoCheckingRepository;

    public KitPoChecking findKitPoCheckingByKitPo(Integer kitPoId) {
        return kitPoCheckingRepository.findKitPoCheckingByKitPo(kitPoId);
    }
}
