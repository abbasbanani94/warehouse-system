package com.who.warehousesystem.controller;

import com.who.warehousesystem.dto.CountryComboDto;
import com.who.warehousesystem.dto.PoComboDto;
import com.who.warehousesystem.service.CountryService;
import com.who.warehousesystem.service.PurchaseOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/po")
public class PurchaseOrderController {

    @Autowired
    PurchaseOrderService purchaseOrderService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CountryService countryService;

    @GetMapping("")
    public ResponseEntity findAllPurchaseOrderCombo () {
        return new ResponseEntity(purchaseOrderService.findAllPurchaseOrders().stream()
        .map(po -> (modelMapper.map(po, PoComboDto.class))), HttpStatus.OK);
    }

    @PostMapping("/{poNo}")
    public ResponseEntity savePurchaseOrder (@PathVariable (value = "poNo") String poNo,
                                             @RequestHeader (value = "userId") Integer userId) throws Exception {
        return new ResponseEntity(modelMapper.map(purchaseOrderService.savePurchaseOrder(poNo, userId),
                PoComboDto.class),HttpStatus.OK);
    }

    @GetMapping("/countries")
    public ResponseEntity findAllCountries () {
        return new ResponseEntity(countryService.findAllCountries().stream().map(country -> (
                modelMapper.map(country, CountryComboDto.class))), HttpStatus.OK);
    }
}
