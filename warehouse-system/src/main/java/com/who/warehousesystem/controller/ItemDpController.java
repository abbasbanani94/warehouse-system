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
        return new ResponseEntity(itemDpService.findAllItemDpDgv().stream().map(itemDp ->
                (modelMapper.map(itemDp, ItemDpDgv.class))), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity saveItemDp (@RequestBody ItemDpSaveDto dto,
                                      @RequestHeader (value = "userId") Integer userId) throws Exception {
        return new ResponseEntity(modelMapper.map(itemDpService.saveItemDp(dto,userId), ItemDpDgv.class), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity editItemDp (@PathVariable (value = "id") Integer id,@RequestBody ItemDpSaveDto dto,
                                      @RequestHeader (value = "userId") Integer userId) throws Exception {
        return new ResponseEntity(modelMapper.map(itemDpService.editItemDp(id,dto,userId), ItemDpDgv.class),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteItemDp (@PathVariable (value = "id") Integer id,
                                        @RequestHeader (value = "userId") Integer userId) throws Exception {
        itemDpService.deleteItemDp(id,userId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity searchItemDp (@RequestParam (value = "planId") String planId,
                                        @RequestParam (value = "date") String date,
                                        @RequestParam (value = "poId") String poId,
                                        @RequestParam (value = "itemPoId") String itemPoId,
                                        @RequestParam (value = "cityId") String cityId,
                                        @RequestParam (value = "districtId") String districtId,
                                        @RequestParam (value = "centerId") String centerId,
                                        @RequestParam (value = "qty") String qty) {
        return new ResponseEntity(itemDpService.searchItemDp(planId,date,poId,itemPoId,cityId,districtId,
                centerId,qty).stream().map(itemDp -> (modelMapper.map(itemDp, ItemDpDgv.class))), HttpStatus.OK);
    }
}
