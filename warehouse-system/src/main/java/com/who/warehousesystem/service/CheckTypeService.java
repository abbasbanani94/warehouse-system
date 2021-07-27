package com.who.warehousesystem.service;

import com.who.warehousesystem.model.CheckType;
import com.who.warehousesystem.repository.CheckTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CheckTypeService {

    @Autowired
    CheckTypeRepository checkTypeRepository;

    public List<CheckType> findAllTypesCombo() {
        return checkTypeRepository.findAllCheckTypes().orElse(new ArrayList<>());
    }

    public CheckType findCheckTypeById(Integer typeId) throws Exception {
        return checkTypeRepository.findCheckTypeById(typeId).orElseThrow(() ->
                new Exception("No Check Type for ID : " + typeId));
    }
}
