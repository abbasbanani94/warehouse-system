package com.who.warehousesystem.controller;

import com.who.warehousesystem.dto.CityComboDto;
import com.who.warehousesystem.model.City;
import com.who.warehousesystem.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    CityService cityService;

    @GetMapping("/combo")
    public ResponseEntity findAllCitiesCombo () throws Exception {
        return new ResponseEntity(cityService.findAllCities().stream().map(city -> {
            return new CityComboDto(city.getId(), city.getEnName() + " - " + city.getArName());
        }).collect(Collectors.toList()), HttpStatus.OK);
    }
}
