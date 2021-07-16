package com.who.warehousesystem.service;

import com.who.warehousesystem.model.KitDetail;
import com.who.warehousesystem.repository.KitDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KitDetailService {

    @Autowired
    KitDetailRepository kitDetailRepository;

    public boolean findKitDetailByKitPo(Integer kitPoId) {
        List<KitDetail> kitDetails = kitDetailRepository.findKitDetailsByKitPo(kitPoId);
        if(kitDetails.size() >= 1)
            return true;
        else
            return false;
    }
}
