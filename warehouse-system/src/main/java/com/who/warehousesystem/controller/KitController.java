package com.who.warehousesystem.controller;

import com.who.warehousesystem.dto.ComboDto;
import com.who.warehousesystem.dto.ItemTbDto;
import com.who.warehousesystem.service.KitService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kits")
public class KitController {

    @Autowired
    KitService kitService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/combo")
    public ResponseEntity findKitsCombo () {
        return new ResponseEntity(kitService.findKitsCombo().stream()
                .map(kit -> (modelMapper.map(kit, ComboDto.class))), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity findItemTbById (@PathVariable(value = "id") Integer id) throws Exception {
        return new ResponseEntity(modelMapper.map(kitService.findKitById(id), ItemTbDto.class), HttpStatus.OK);
    }
}
