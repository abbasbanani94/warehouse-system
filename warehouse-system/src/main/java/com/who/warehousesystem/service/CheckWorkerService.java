package com.who.warehousesystem.service;

import com.who.warehousesystem.model.CheckWorker;
import com.who.warehousesystem.repository.CheckWorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CheckWorkerService {

    @Autowired
    CheckWorkerRepository checkWorkerRepository;

    public List<CheckWorker> findCheckWorkersByWorker(Integer workerId) {
        return checkWorkerRepository.findCheckWorkersByWorker(workerId).orElse(new ArrayList<>());
    }

    public List<CheckWorker> findCheckWorkersByCheck(Integer checkId) {
        return checkWorkerRepository.findCheckWorkersByCheck(checkId).orElse(new ArrayList<>());
    }
}
