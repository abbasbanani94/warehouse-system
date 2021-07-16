package com.who.warehousesystem.service;

import com.who.warehousesystem.repository.CheckTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckTypeService {

    @Autowired
    CheckTypeRepository checkTypeRepository;
}
