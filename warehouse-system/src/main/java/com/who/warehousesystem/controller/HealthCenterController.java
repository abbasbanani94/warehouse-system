package com.who.warehousesystem.controller;

import com.who.warehousesystem.dto.CenterDgvDto;
import com.who.warehousesystem.dto.CenterEditDto;
import com.who.warehousesystem.dto.CenterSaveDto;
import com.who.warehousesystem.service.HealthCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/healthCenters")
public class HealthCenterController {

    @Autowired
    HealthCenterService healthCenterService;

    @GetMapping("")
    public ResponseEntity findAllHealthCentersDgv () {
        return new ResponseEntity(healthCenterService.findAllHealthCenters().stream().map(center -> {
            return new CenterDgvDto(center.getId(),
                    center.getDistrict().getCity().getEnName() + " - " + center.getDistrict().getCity().getArName(),
                    center.getDistrict().getEnName() + " - " + center.getDistrict().getArName(),
                    center.getEnName(),center.getArName());
        }).collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity saveHealthCenter (@RequestBody CenterSaveDto dto,
                                            @RequestHeader (value = "userId") Integer userId) throws Exception {
        return new ResponseEntity(healthCenterService.saveHealthCenter(dto,userId), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity editHealthCenter (@PathVariable (value = "id") Integer id,@RequestBody CenterEditDto dto,
                                            @RequestHeader (value = "userId") Integer userId) throws Exception {
        return new ResponseEntity(healthCenterService.editHealthCenter(id,dto,userId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteHealthCenter (@PathVariable (value = "id") Integer id,
                                              @RequestHeader (value = "userId") Integer userId) throws Exception {
        healthCenterService.deleteHealthCenter(id,userId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity searchHealthCenter (@RequestParam (value = "cityName") String cityName,
                                              @RequestParam (value = "districtName") String districtName,
                                              @RequestParam (value = "enName") String enName,
                                              @RequestParam (value = "arName") String arName) {
        return new ResponseEntity(healthCenterService.searchHealthCenter(cityName,districtName,enName,arName).stream()
                .map(center -> {
            return new CenterDgvDto(center.getId(),
                    center.getDistrict().getCity().getEnName() + " - " + center.getDistrict().getCity().getArName(),
                    center.getDistrict().getEnName() + " - " + center.getDistrict().getArName(),
                    center.getEnName(),center.getArName());
                }).collect(Collectors.toList()),HttpStatus.OK);
    }
}
