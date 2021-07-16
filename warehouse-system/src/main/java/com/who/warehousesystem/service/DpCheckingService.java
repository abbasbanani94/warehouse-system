package com.who.warehousesystem.service;

import com.who.warehousesystem.repository.DpCheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DpCheckingService {

    @Autowired
    DpCheckingRepository dpCheckingRepository;
}
