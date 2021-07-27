package com.who.warehousesystem.service;

import com.who.warehousesystem.model.Check;
import com.who.warehousesystem.model.CheckItemPo;
import com.who.warehousesystem.model.ItemPo;
import com.who.warehousesystem.model.User;
import com.who.warehousesystem.repository.CheckItemPoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CheckItemPoService {

    @Autowired
    CheckItemPoRepository checkItemPoRepository;

    public List<CheckItemPo> findCheckItemsByItemPo(Integer itemPoId) {
        return checkItemPoRepository.findCheckItemsByItemPo(itemPoId).orElse(new ArrayList<>());
    }

    public List<CheckItemPo> findCheckItemsByCheck(Integer checkId) {
        return checkItemPoRepository.findCheckItemsByCheck(checkId).orElse(new ArrayList<>());
    }

    public boolean deleteItemsByCheck(Integer checkId, User user) {
        List<CheckItemPo> list = checkItemPoRepository.findCheckItemsByCheck(checkId).orElse(new ArrayList<>());
        for(CheckItemPo c : list) {
            c.setActive(false);
            c.setUpdatedBy(user);
        }
        checkItemPoRepository.saveAll(list);
        return true;
    }

    public void saveCheckItems(Check check, List<ItemPo> itemPos, User user) {
        List<CheckItemPo> list = new ArrayList<>();
        for(ItemPo po : itemPos) {
            list.add(new CheckItemPo(check,po,user));
        }
        checkItemPoRepository.saveAll(list);
    }
}
