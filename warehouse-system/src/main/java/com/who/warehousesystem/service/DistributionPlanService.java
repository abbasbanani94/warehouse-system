package com.who.warehousesystem.service;

import com.who.warehousesystem.dto.ItemDpSaveDto;
import com.who.warehousesystem.model.DistributionPlan;
import com.who.warehousesystem.model.User;
import com.who.warehousesystem.repository.DistributionPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DistributionPlanService {

    @Autowired
    DistributionPlanRepository distributionPlanRepository;

    public List<DistributionPlan> findDpCombo() {
        return distributionPlanRepository.findAllDps().orElse(new ArrayList<>());
    }

    public DistributionPlan findDpByItemDp(ItemDpSaveDto dto, User user) throws Exception {
        if(dto.getPlanId() != null && dto.getPlanId() != 0)
            return distributionPlanRepository.findDpById(dto.getPlanId()).orElseThrow(() ->
                    new Exception("No Plan for ID : " + dto.getPlanId()));
        else {
            DistributionPlan dp = distributionPlanRepository.findDpByNamesAndDate(dto.getEnName(),dto.getArName(),
                    dto.getPlanDate());
            if(dp != null)
                return dp;
            else {
                dp = new DistributionPlan(dto,user);
                return distributionPlanRepository.save(dp);
            }
        }
    }

    public DistributionPlan findDpById(Integer id) throws Exception {
        return distributionPlanRepository.findDpById(id).orElseThrow(() ->
                new Exception("No DP for ID : " + id));
    }
}
