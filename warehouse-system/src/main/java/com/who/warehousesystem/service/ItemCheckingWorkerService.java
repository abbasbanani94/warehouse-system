package com.who.warehousesystem.service;

import com.who.warehousesystem.repository.ItemCheckingWorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemCheckingWorkerService {

    @Autowired
    ItemCheckingWorkerRepository itemCheckingWorkerRepository;
}
