package com.who.warehousesystem.controller;

import com.who.warehousesystem.dto.*;
import com.who.warehousesystem.model.KitPo;
import com.who.warehousesystem.service.KitPoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/kitPo")
public class KitPoController {

    @Autowired
    KitPoService kitPoService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("")
    public ResponseEntity findKitPoDgv () {
        return new ResponseEntity(kitPoService.findKitPoDgv().stream()
                .map(kitPo -> (modelMapper.map(kitPo, KitPoDgv.class))), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity saveKitPo (@RequestBody KitPoSaveDto dto,
                                     @RequestHeader (value = "userId") Integer userId) throws Exception {
        return new ResponseEntity(modelMapper.map(kitPoService.saveKitPo(dto,userId),KitPoDgv.class),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity editKitPo (@PathVariable (value = "id") Integer id,@RequestBody KitPoSaveDto dto,
                                     @RequestHeader (value = "userId") Integer userId) throws Exception {
        return new ResponseEntity(modelMapper.map(kitPoService.editKitPo(id,dto,userId),KitPoDgv.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteKitPo (@PathVariable (value = "id") Integer id,
                                       @RequestHeader (value = "userId") Integer userId) throws Exception {
        kitPoService.deleteKitPo(id,userId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity searchKitPo (@RequestParam (value = "poId") String poId,
                                       @RequestParam (value = "dateReceived") String dateReceived,
                                       @RequestParam (value = "kitId") String kitId,
                                       @RequestParam (value = "minTemp") String minTemp,
                                       @RequestParam (value = "maxTemp") String maxTemp,
                                       @RequestParam (value = "description") String description,
                                       @RequestParam (value = "manDate") String manDate,
                                       @RequestParam (value = "expDate") String expDate,
                                       @RequestParam (value = "country") String country,
                                       @RequestParam (value = "batchNo") String batchNo,
                                       @RequestParam (value = "location") String location,
                                       @RequestParam (value = "palletsQty") String palletsQty,
                                       @RequestParam (value = "boxesPallets") String boxesPallets,
                                       @RequestParam (value = "kitsPallet") String kitsPallet,
                                       @RequestParam (value = "totalQty") String totalQty,
                                       @RequestParam (value = "kitType") String kitType) {
        KitPoSearchDto dto = new KitPoSearchDto(poId,dateReceived,kitId,minTemp,maxTemp,description,manDate,expDate,
                country,batchNo,location,palletsQty,boxesPallets,kitsPallet,totalQty,kitType);
        return new ResponseEntity(kitPoService.searchKitPo(dto).stream()
                .map(kitPo -> (modelMapper.map(kitPo, KitPoDgv.class))), HttpStatus.OK);
    }

    @GetMapping("/combo/{poId}")
    public ResponseEntity findKitsPoComboByPo (@PathVariable (value = "poId") Integer poId) throws Exception {
        return new ResponseEntity(kitPoService.findKitsPoComboByPo(poId).stream().map(kitPo -> {
            return new ComboDto(kitPo.getId(), kitPo.getKit().getName());
        }).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/kitPoDp/{kitPoId}")
    public ResponseEntity findItemPoDpDetailsById (@PathVariable (value = "kitPoId") Integer kitPoId) throws Exception {
        KitPo kitPo = kitPoService.findKitPoById(kitPoId);
        KitPoDpDto dto = new KitPoDpDto(kitPo.getRecDate(), kitPo.getBatchNo(), kitPo.getKit().getDescription(),
                kitPo.getManDate(), kitPo.getExpDate(), kitPo.getKit().getKitType().getName(), kitPo.getTotalQty(),
                kitPo.getInventory());
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    @GetMapping("/name/{id}")
    public ResponseEntity findKitNameByKitPo (@PathVariable (value = "id") Integer id) throws Exception {
        return new ResponseEntity(new NameDto(kitPoService.findKitNameByKitPo(id)), HttpStatus.OK);
    }
}
