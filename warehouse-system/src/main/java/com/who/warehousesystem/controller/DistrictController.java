package com.who.warehousesystem.controller;

import com.who.warehousesystem.dto.DistrictComboDto;
import com.who.warehousesystem.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/districts")
public class DistrictController {

    @Autowired
    DistrictService districtService;

    @GetMapping("/combo/{cityId}")
    public ResponseEntity findAllDistrictsCombo (@PathVariable (value = "cityId") Integer cityId) throws Exception {
        return new ResponseEntity(districtService.findAllDistrictsByCity(cityId).stream().map(district -> {
            return new DistrictComboDto(district.getId(),
                    district.getEnName() + " - " + district.getArName());
        }).collect(Collectors.toList()), HttpStatus.OK);
    }
}
