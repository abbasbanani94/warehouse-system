package com.who.warehousesystem.service;

import com.who.warehousesystem.repository.DistributionPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DistributionPlanService {

    @Autowired
    DistributionPlanRepository distributionPlanRepository;
}
