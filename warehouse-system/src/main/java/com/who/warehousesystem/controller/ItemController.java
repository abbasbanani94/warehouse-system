package com.who.warehousesystem.controller;

import com.who.warehousesystem.dto.ComboDto;
import com.who.warehousesystem.dto.ItemSaveDto;
import com.who.warehousesystem.dto.ItemTbDto;
import com.who.warehousesystem.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    ItemService itemService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/combo")
    public ResponseEntity findItemsCombo () {
        return new ResponseEntity(itemService.findItemsCombo().stream().map(
                item -> (modelMapper.map(item, ComboDto.class))), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity findItemTbById (@PathVariable (value = "id") Integer id) throws Exception {
        return new ResponseEntity(modelMapper.map(itemService.findItemById(id), ItemTbDto.class), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity saveItem (@RequestBody ItemSaveDto dto, @RequestHeader (value = "userId") Integer userId)
            throws Exception {
        return new ResponseEntity(itemService.saveItem(dto,userId), HttpStatus.OK);
    }
}
