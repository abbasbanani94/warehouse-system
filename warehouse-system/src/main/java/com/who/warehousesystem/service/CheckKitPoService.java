package com.who.warehousesystem.service;

import com.who.warehousesystem.repository.CheckKitPoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckKitPoService {

    @Autowired
    CheckKitPoRepository checkKitPoRepository;
}
