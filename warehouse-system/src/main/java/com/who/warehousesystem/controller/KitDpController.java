package com.who.warehousesystem.controller;

import com.who.warehousesystem.dto.KitDpDgv;
import com.who.warehousesystem.service.KitDpService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kitDp")
public class KitDpController {

    @Autowired
    KitDpService kitDpService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("")
    public ResponseEntity findAllKitDpDgv () {
        return new ResponseEntity(kitDpService.findAllKitDpDgv().stream().map(
                kitDp -> (modelMapper.map(kitDp, KitDpDgv.class))), HttpStatus.OK);
    }
}
