package com.who.warehousesystem.service;

import com.who.warehousesystem.repository.CheckWorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckWorkerService {

    @Autowired
    CheckWorkerRepository checkWorkerRepository;
}
