package com.who.warehousesystem.service;

import com.who.warehousesystem.model.ItemCheckingWorker;
import com.who.warehousesystem.repository.ItemCheckingWorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ItemCheckingWorkerService {

    @Autowired
    ItemCheckingWorkerRepository itemCheckingWorkerRepository;

    public List<ItemCheckingWorker> findAllItemsCheckingByWorker(Integer workerId) {
        return itemCheckingWorkerRepository.findAllItemsCheckingByWorker(workerId).orElse(new ArrayList<>());
    }
}
