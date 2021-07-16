package com.who.warehousesystem.service;

import com.who.warehousesystem.model.DistributionPlan;
import com.who.warehousesystem.repository.DistributionPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DistributionPlanService {

    @Autowired
    DistributionPlanRepository distributionPlanRepository;

    public List<DistributionPlan> findDistributionPlansCombo() {
        return distributionPlanRepository.findAllDistributionPlans().orElse(new ArrayList<>());
    }
}
