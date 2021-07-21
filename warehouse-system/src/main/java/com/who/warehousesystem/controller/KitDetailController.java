package com.who.warehousesystem.controller;

import com.who.warehousesystem.dto.KitDetailDgvDto;
import com.who.warehousesystem.dto.KitDetailSaveDto;
import com.who.warehousesystem.service.KitDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/kitDetails")
public class KitDetailController {

    @Autowired
    KitDetailService kitDetailService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/{kitPoId}")
    public ResponseEntity findAllKitDetailsByKitPoDgv (@PathVariable (value = "kitPoId") Integer kitPoId) {
        return new ResponseEntity(kitDetailService.findAllKitDetailsByKitPoDgv(kitPoId).stream().map(
        kitDetail -> (modelMapper.map(kitDetail, KitDetailDgvDto.class))), HttpStatus.OK);
    }

    @PostMapping("/{kitPoId}")
    public ResponseEntity saveKitDetail (@PathVariable (value = "kitPoId") Integer kitPoId,
                                         @RequestBody KitDetailSaveDto dto,
                                         @RequestHeader (value = "userId") Integer userId) throws Exception {
        return new ResponseEntity(modelMapper.map(kitDetailService.saveKitDetail(kitPoId,dto,userId),
                KitDetailDgvDto.class), HttpStatus.OK);
    }

    @PutMapping("/{kitPoId}/{detailId}")
    public ResponseEntity editKitDetail (@PathVariable (value = "kitPoId") Integer kitPoId,
                                         @PathVariable (value = "detailId") Integer detailId,
                                         @RequestBody KitDetailSaveDto dto,
                                         @RequestHeader (value = "userId") Integer userId) throws Exception {
        return new ResponseEntity(modelMapper.map(kitDetailService.editKitDetail(kitPoId,detailId,dto,userId),
                KitDetailDgvDto.class), HttpStatus.OK);
    }

    @DeleteMapping("/{detailId}")
    public ResponseEntity deleteKitDetail (@PathVariable (value = "detailId") Integer detailId,
                                           @RequestHeader (value = "userId") Integer userId) throws Exception {
        kitDetailService.deleteKitDetail(detailId,userId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity searchKitDetails (@RequestParam(value = "kitPoId") String kitPoId,
                                            @RequestParam (value = "boxNo") String boxNo,
                                            @RequestParam (value = "minTemp") String minTemp,
                                            @RequestParam (value = "maxTemp") String maxTemp,
                                            @RequestParam (value = "description") String description,
                                            @RequestParam (value = "packaging") String packaging,
                                            @RequestParam (value = "packs") String packs,
                                            @RequestParam (value = "pieces") String pieces,
                                            @RequestParam (value = "expDate") String expDate,
                                            @RequestParam (value = "itemId") String itemId) {
        return new ResponseEntity(kitDetailService.searchKitDetails(kitPoId,boxNo,minTemp,maxTemp,description,
                packaging,packs,pieces,expDate,itemId).stream().map(kitDetail -> (modelMapper.map(kitDetail,
                KitDetailDgvDto.class))), HttpStatus.OK);
    }
}
