package com.who.warehousesystem.controller;

import com.who.warehousesystem.dto.ComboDto;
import com.who.warehousesystem.service.DistributionPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/dp")
public class DistributionPlanController {

    @Autowired
    DistributionPlanService distributionPlanService;

    @GetMapping("/combo")
    public ResponseEntity findDistributionPlansCombo () {
        return new ResponseEntity(distributionPlanService.findDistributionPlansCombo().stream()
                .map(distributionPlan -> {
                    return new ComboDto(distributionPlan.getId(),distributionPlan.getEnName());
                }).collect(Collectors.toList()), HttpStatus.OK);
    }
}
