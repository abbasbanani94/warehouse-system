package com.who.warehousesystem.service;

import com.who.warehousesystem.model.Check;
import com.who.warehousesystem.model.CheckWorker;
import com.who.warehousesystem.model.User;
import com.who.warehousesystem.model.Worker;
import com.who.warehousesystem.repository.CheckWorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public boolean deleteCheckWorkersByCheck(Integer checkId, User user) {
        List<CheckWorker> checkWorkers = checkWorkerRepository.findCheckWorkersByCheck(checkId).orElse(new ArrayList<>());
        for(CheckWorker cw : checkWorkers) {
            cw.setActive(false);
            cw.setUpdatedBy(user);
        }
        checkWorkerRepository.saveAll(checkWorkers);
        return true;
    }

    public void saveCheckWorkers(Check check, List<Worker> workers, User user) {
        List<CheckWorker> checkWorkers = new ArrayList<>();
        for (Worker w : workers) {
            CheckWorker cw = new CheckWorker(check,w,user);
            checkWorkers.add(cw);
        }
        checkWorkerRepository.saveAll(checkWorkers);
    }
}
