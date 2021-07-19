package com.who.warehousesystem.controller;

import com.who.warehousesystem.dto.KitDpDgv;
import com.who.warehousesystem.dto.KitDpSaveDto;
import com.who.warehousesystem.service.KitDpService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("")
    public ResponseEntity saveKitDp (@RequestBody KitDpSaveDto dto,
                                     @RequestHeader (value = "userId") Integer userId) throws Exception {
        return new ResponseEntity(modelMapper.map(kitDpService.saveKitDp(dto,userId), KitDpDgv.class), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity editKitDp (@PathVariable (value = "id") Integer id,@RequestBody KitDpSaveDto dto,
                                     @RequestHeader (value = "userId") Integer userId) throws Exception {
        return new ResponseEntity(modelMapper.map(kitDpService.editKitDp(id,dto,userId), KitDpDgv.class), HttpStatus.OK);
    }
}
