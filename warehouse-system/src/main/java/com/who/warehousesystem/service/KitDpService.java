package com.who.warehousesystem.service;

import com.who.warehousesystem.model.KitDp;
import com.who.warehousesystem.repository.KitDpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KitDpService {

    @Autowired
    KitDpRepository kitDpRepository;

    public KitDp findKitDpByKitPo(Integer kitPoId) {
        return kitDpRepository.findKitDpByKitPo(kitPoId);
    }

    public boolean findKitDpByHealthCenter(Integer healthCenterId) {
        List<KitDp> kitDps = kitDpRepository.findKitDpByHealthCenter(healthCenterId);
        if(kitDps.size() >= 1)
            return true;
        else
            return false;
    }
}
