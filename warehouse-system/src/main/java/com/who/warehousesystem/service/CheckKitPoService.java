package com.who.warehousesystem.service;

import com.who.warehousesystem.model.CheckKitPo;
import com.who.warehousesystem.repository.CheckKitPoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CheckKitPoService {

    @Autowired
    CheckKitPoRepository checkKitPoRepository;

    public List<CheckKitPo> findCheckKitsByKitPo(Integer kitPoId) {
        return checkKitPoRepository.findCheckKitsByKitPo(kitPoId).orElse(new ArrayList<>());
    }

    public List<CheckKitPo> findCheckKitsByCheck(Integer checkId) {
        return checkKitPoRepository.findCheckKitsByCheck(checkId).orElse(new ArrayList<>());
    }
}
