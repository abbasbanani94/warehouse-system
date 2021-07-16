package com.who.warehousesystem.service;

import com.who.warehousesystem.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkerService {

    @Autowired
    WorkerRepository workerRepository;
}
