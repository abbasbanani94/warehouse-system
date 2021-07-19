package com.who.warehousesystem.service;

import com.who.warehousesystem.model.Waybill;
import com.who.warehousesystem.repository.WaybillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WaybillService {

    @Autowired
    WaybillRepository waybillRepository;

    public boolean findWaybillByHealthCenter(Integer healthCenterId) {
        List<Waybill> waybills = waybillRepository.findWaybillByHealthCenter(healthCenterId);
        if(waybills.size() >= 1)
            return true;
        else
            return false;
    }

    public List<Waybill> findAllWaybills() {
        return waybillRepository.findAllWaybills().orElse(new ArrayList<>());
    }
}
