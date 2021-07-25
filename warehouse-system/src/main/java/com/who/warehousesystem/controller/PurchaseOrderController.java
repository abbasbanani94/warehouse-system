package com.who.warehousesystem.controller;

import com.who.warehousesystem.dto.ComboDto;
import com.who.warehousesystem.dto.PoComboDto;
import com.who.warehousesystem.dto.PoDgvDto;
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

    @PostMapping("")
    public ResponseEntity savePurchaseOrder (@RequestBody Integer poNo,
                                             @RequestHeader (value = "userId") Integer userId) throws Exception {
        return new ResponseEntity(modelMapper.map(purchaseOrderService.savePurchaseOrder(poNo, userId),
                PoComboDto.class),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity editPurchaseOrder (@PathVariable (value = "id") Integer id,
                                             @RequestBody Integer poNo,
                                             @RequestHeader (value = "userId") Integer userId) throws Exception {
        return new ResponseEntity(modelMapper.map(purchaseOrderService.editPurchaseOrder(id,poNo,userId),
                PoComboDto.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePurchaseOrder (@PathVariable (value = "id") Integer id,
                                               @RequestHeader (value = "userId") Integer userId) throws Exception {
        purchaseOrderService.deletePurchaseOrder(id,userId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/countries")
    public ResponseEntity findAllCountries () {
        return new ResponseEntity(countryService.findAllCountries().stream().map(country -> (
                modelMapper.map(country, ComboDto.class))), HttpStatus.OK);
    }

    @GetMapping("/dgv")
    public ResponseEntity findAllPoDgv () {
        return new ResponseEntity(purchaseOrderService.findAllPoDgv(), HttpStatus.OK);
    }

    @GetMapping("/search/{no}")
    public ResponseEntity searchAllPoDgv(@PathVariable (value = "no") Integer no) {
        return new ResponseEntity(purchaseOrderService.searchAllPoDgv(no).stream().map(po -> (
                modelMapper.map(po, PoDgvDto.class))),HttpStatus.OK);
    }
}
