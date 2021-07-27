package com.who.warehousesystem.controller;

import com.who.warehousesystem.dto.ComboDto;
import com.who.warehousesystem.service.CheckTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/checkTypes")
public class CheckTypeController {

    @Autowired
    CheckTypeService checkTypeService;

    @GetMapping("/combo")
    public ResponseEntity findAllTypesCombo () {
        return new ResponseEntity(checkTypeService.findAllTypesCombo().stream().map(checkType -> {
            return new ComboDto(checkType.getId(),checkType.getEnName());
        }).collect(Collectors.toList()), HttpStatus.OK);
    }
}
