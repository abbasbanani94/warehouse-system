package com.who.warehousesystem.service;

import com.who.warehousesystem.model.Check;
import com.who.warehousesystem.model.CheckKitPo;
import com.who.warehousesystem.model.KitPo;
import com.who.warehousesystem.model.User;
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

    public boolean deleteKitsByCheck(Integer checkId, User user) {
        List<CheckKitPo> list = checkKitPoRepository.findCheckKitsByCheck(checkId).orElse(new ArrayList<>());
        for(CheckKitPo k : list) {
            k.setActive(false);
            k.setUpdatedBy(user);
        }
        checkKitPoRepository.saveAll(list);
        return true;
    }

    public void saveCheckKits(Check check, List<KitPo> kitPos, User user) {
        List<CheckKitPo> list = new ArrayList<>();
        for(KitPo kitPo : kitPos) {
            list.add(new CheckKitPo(check,kitPo,user));
        }
        checkKitPoRepository.saveAll(list);
    }
}
