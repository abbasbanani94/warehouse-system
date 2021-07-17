package com.who.warehousesystem.controller;

import com.who.warehousesystem.dto.ItemDpDgv;
import com.who.warehousesystem.dto.ItemDpSaveDto;
import com.who.warehousesystem.service.ItemDpService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/itemDp")
public class ItemDpController {

    @Autowired
    ItemDpService itemDpService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("")
    public ResponseEntity findAllItemDpDgv () {
        return new ResponseEntity(itemDpService.findAllItemDp().stream().map(itemDp ->
                (modelMapper.map(itemDp, ItemDpDgv.class))), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity saveItemDp (@RequestBody ItemDpSaveDto dto,
                                      @RequestHeader (value = "userId") Integer userId) throws Exception {
        return new ResponseEntity(modelMapper.map(itemDpService.saveItemDp(dto,userId), ItemDpDgv.class), HttpStatus.OK);
    }
}
