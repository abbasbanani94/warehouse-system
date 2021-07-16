package com.who.warehousesystem.service;

import com.who.warehousesystem.repository.KitWbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KitWbService {

    @Autowired
    KitWbRepository kitWbRepository;
}
