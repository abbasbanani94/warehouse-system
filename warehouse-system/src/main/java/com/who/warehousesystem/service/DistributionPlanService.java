package com.who.warehousesystem.service;

import com.who.warehousesystem.dto.ItemDpSaveDto;
import com.who.warehousesystem.model.DistributionPlan;
import com.who.warehousesystem.model.User;
import com.who.warehousesystem.repository.DistributionPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DistributionPlanService {

    @Autowired
    DistributionPlanRepository distributionPlanRepository;

    public List<DistributionPlan> findDpCombo() {
        return distributionPlanRepository.findAllDps().orElse(new ArrayList<>());
    }

    public DistributionPlan findDpByDetails (Integer planId, String enName, String arName, LocalDate planDate, User user)
            throws Exception {
        if(planId != null && planId != 0)
            return distributionPlanRepository.findDpById(planId).orElseThrow(() ->
                    new Exception("No Plan for ID : " + planId));
        else {
            DistributionPlan dp = distributionPlanRepository.findDpByNamesAndDate(enName,arName,planDate);
            if(dp != null)
                return dp;
            else {
                dp = new DistributionPlan(arName,enName,planDate,user);
                return distributionPlanRepository.save(dp);
            }
        }
    }

    public DistributionPlan findDpById(Integer id) throws Exception {
        return distributionPlanRepository.findDpById(id).orElseThrow(() ->
                new Exception("No DP for ID : " + id));
    }
}
