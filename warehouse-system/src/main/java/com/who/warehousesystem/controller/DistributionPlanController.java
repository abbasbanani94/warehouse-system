package com.who.warehousesystem.controller;

import com.who.warehousesystem.dto.ComboDto;
import com.who.warehousesystem.dto.DpDetailsDto;
import com.who.warehousesystem.dto.PlanDgvDto;
import com.who.warehousesystem.dto.PlanSaveDto;
import com.who.warehousesystem.service.DistributionPlanService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
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

    @GetMapping("/dgv")
    public ResponseEntity findAllDpDgv () {
        return new ResponseEntity(distributionPlanService.findAllDpDgv().stream().map(dp ->
                modelMapper.map(dp, PlanDgvDto.class)), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity saveDistributionPlan (@RequestBody PlanSaveDto dto,
                                                @RequestHeader (value = "userId") Integer userId) throws Exception {
        return new ResponseEntity(modelMapper.map(distributionPlanService.saveDistributionPlan(dto,userId),
                PlanDgvDto.class), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity editDistributionPlan (@PathVariable (value = "id") Integer id,
                                                @RequestBody PlanSaveDto dto,
                                                @RequestHeader (value = "userId") Integer userId) throws Exception {
        return new ResponseEntity(modelMapper.map(distributionPlanService.editDistributionPlan(id,dto,userId),
                PlanDgvDto.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDistributionPlan (@PathVariable (value = "id") Integer id,
                                                  @RequestHeader (value = "userId") Integer userId) throws Exception {
        distributionPlanService.deleteDistributionPlan(id,userId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity searchDistributionPlan (@RequestParam (value = "enName") String enName,
                                                  @RequestParam (value = "arName") String arName,
                                                  @RequestParam (value = "date") String date,
                                                  @RequestParam (value = "d") boolean d) {
        return new ResponseEntity(distributionPlanService.searchDistributionPlan(enName,arName,date,d).stream()
                .map(dp -> (modelMapper.map(dp,PlanDgvDto.class))), HttpStatus.OK);
    }
}
