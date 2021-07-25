package com.who.warehousesystem.service;

import com.who.warehousesystem.model.KitCheckingWorker;
import com.who.warehousesystem.repository.KitCheckingWorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class KitCheckingWorkerService {

    @Autowired
    KitCheckingWorkerRepository kitCheckingWorkerRepository;

    public List<KitCheckingWorker> findAllKitsCheckingByWorker(Integer workerId) {
        return kitCheckingWorkerRepository.findAllKitsCheckingByWorker(workerId).orElse(new ArrayList<>());
    }
}
