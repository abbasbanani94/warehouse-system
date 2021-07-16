package com.who.warehousesystem.service;

import com.who.warehousesystem.repository.KitCheckingWorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KitCheckingWorkerService {

    @Autowired
    KitCheckingWorkerRepository kitCheckingWorkerRepository;
}
