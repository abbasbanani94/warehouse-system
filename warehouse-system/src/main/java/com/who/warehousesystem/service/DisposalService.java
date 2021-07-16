package com.who.warehousesystem.service;

import com.who.warehousesystem.repository.DisposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisposalService {

    @Autowired
    DisposalRepository disposalRepository;
}
