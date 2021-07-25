package com.who.warehousesystem.service;

import com.who.warehousesystem.model.DpCheckingWorker;
import com.who.warehousesystem.repository.DpCheckingWorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class DpCheckingWorkerService {

    @Autowired
    DpCheckingWorkerRepository dpCheckingWorkerRepository;

    public List<DpCheckingWorker> findAllKitsCheckingByWorker(Integer workerId) {
        return dpCheckingWorkerRepository.findAllDpCheckingWorkersByWorker(workerId).orElse(new ArrayList<>());
    }
}
