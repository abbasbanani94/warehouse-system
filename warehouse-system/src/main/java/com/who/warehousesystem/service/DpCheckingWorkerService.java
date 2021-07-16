package com.who.warehousesystem.service;

import com.who.warehousesystem.repository.DpCheckingWorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DpCheckingWorkerService {

    @Autowired
    DpCheckingWorkerRepository dpCheckingWorkerRepository;
}
