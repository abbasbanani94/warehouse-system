package com.who.warehousesystem.service;

import com.who.warehousesystem.repository.CheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckService {

    @Autowired
    CheckRepository checkRepository;
}
