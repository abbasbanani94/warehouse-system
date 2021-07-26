package com.who.warehousesystem.service;

import com.who.warehousesystem.repository.CheckItemPoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckItemPoService {

    @Autowired
    CheckItemPoRepository checkItemPoRepository;
}
