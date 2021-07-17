package com.who.warehousesystem.controller;

import com.who.warehousesystem.dto.ComboDto;
import com.who.warehousesystem.dto.DpDetailsDto;
import com.who.warehousesystem.service.DistributionPlanService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/dp")
public class DistributionPlanController {

    @Autowired
    DistributionPlanService distributionPlanService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/combo")
    public ResponseEntity findDpCombo () {
        return new ResponseEntity(distributionPlanService.findDpCombo().stream()
                .map(distributionPlan -> {
                    return new ComboDto(distributionPlan.getId(),distributionPlan.getEnName());
                }).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/details/{id}")
    public ResponseEntity findDpDetails (@PathVariable (value = "id") Integer id) throws Exception {
        return new ResponseEntity(modelMapper.map(distributionPlanService.findDpById(id), DpDetailsDto.class),
                HttpStatus.OK);
    }
}
