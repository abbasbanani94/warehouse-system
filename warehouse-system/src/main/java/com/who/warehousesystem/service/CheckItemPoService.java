package com.who.warehousesystem.service;

import com.who.warehousesystem.model.CheckItemPo;
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
}
