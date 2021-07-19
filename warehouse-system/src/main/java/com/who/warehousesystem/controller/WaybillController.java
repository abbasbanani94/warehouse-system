package com.who.warehousesystem.controller;

import com.who.warehousesystem.dto.ComboDto;
import com.who.warehousesystem.service.WaybillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/waybills")
public class WaybillController {

    @Autowired
    WaybillService waybillService;

    @GetMapping("/combo")
    public ResponseEntity findAllWaybillsCombo () {
        return new ResponseEntity(waybillService.findAllWaybills().stream().map(wb -> {
            return new ComboDto(wb.getId(), wb.getFormNo().toString());
        }).collect(Collectors.toList()), HttpStatus.OK);
    }
}
