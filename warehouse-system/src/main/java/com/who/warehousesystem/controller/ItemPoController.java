package com.who.warehousesystem.controller;

import com.who.warehousesystem.dto.*;
import com.who.warehousesystem.model.ItemPo;
import com.who.warehousesystem.service.ItemPoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/itemPo")
public class ItemPoController {

    @Autowired
    ItemPoService itemPoService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("")
    public ResponseEntity findItemPoDgv () {
        return new ResponseEntity(itemPoService.findItemPoDgv().stream()
                .map(itemPo -> (modelMapper.map(itemPo, ItemPoDgv.class))), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity saveItemPo (@RequestBody ItemPoSaveDto itemPoSaveDto,
                                      @RequestHeader (value = "userId") Integer userId) throws Exception {
        return new ResponseEntity(modelMapper.map(itemPoService.saveItemPo(itemPoSaveDto,userId), ItemPoDgv.class),
                HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity editItemPo (@PathVariable Integer id,@RequestBody ItemPoSaveDto dto,
                                      @RequestHeader (value = "userId") Integer userId) throws Exception {
        return new ResponseEntity(modelMapper.map(itemPoService.editItemPo(id,dto,userId), ItemPoDgv.class),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteItemPo (@PathVariable Integer id,
                                        @RequestHeader (value = "userId") Integer userId) throws Exception {
        itemPoService.deleteItemPo(id,userId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity searchItemPo (@RequestParam (value = "poId") String poId,
                                        @RequestParam (value = "dateReceived") String dateReceived,
                                        @RequestParam (value = "itemId") String itemId,
                                        @RequestParam (value = "minTemp") String minTemp,
                                        @RequestParam (value = "maxTemp") String maxTemp,
                                        @RequestParam (value = "description") String description,
                                        @RequestParam (value = "manDate") String manDate,
                                        @RequestParam (value = "expDate") String expDate,
                                        @RequestParam (value = "country") String country,
                                        @RequestParam (value = "batch") String batch,
                                        @RequestParam (value = "packaging") String packaging,
                                        @RequestParam (value = "pallets") String pallets,
                                        @RequestParam (value = "boxes") String boxes,
                                        @RequestParam (value = "packs") String packs,
                                        @RequestParam (value = "totalQty") String totalQty,
                                        @RequestParam (value = "location") String location,
                                        @RequestParam (value = "rec") boolean rec,
                                        @RequestParam (value = "man") boolean man,
                                        @RequestParam (value = "exp") boolean exp) throws Exception {
        ItemPoSearchDto dto = new ItemPoSearchDto(poId,dateReceived,itemId
        ,minTemp,maxTemp,description,manDate,expDate,country,batch,packaging,pallets,boxes,packs,totalQty,location,
                rec,man,exp);
        return new ResponseEntity(itemPoService.searchItemPo(dto).stream()
                .map(itemPo -> (modelMapper.map(itemPo, ItemPoDgv.class))), HttpStatus.OK);
    }

    @GetMapping("/combo/{poId}")
    public ResponseEntity findItemsPoComboByPo (@PathVariable (value = "poId") Integer poId) throws Exception {
        return new ResponseEntity(itemPoService.findItemsPoComboByPo(poId).stream().map(itemPo -> {
            return new ComboDto(itemPo.getId(), itemPo.getItem().getName());
        }).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/itemPoDp/{itemPoId}")
    public ResponseEntity findItemPoDpDetailsById (@PathVariable (value = "itemPoId") Integer itemPoId) throws Exception {
        ItemPo itemPo = itemPoService.findItemPoById(itemPoId);
        ItemPoDpDto dto = new ItemPoDpDto(itemPo.getRecDate(),itemPo.getBatchNo(),itemPo.getItem().getDescription(),
                itemPo.getManDate(),itemPo.getExpDate(),itemPo.getPackaging(),itemPo.getTotalQty(),itemPo.getInventory());
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    @GetMapping("/app/details/{id}")
    public ResponseEntity findItemPoDetailsForApp (@PathVariable (value = "id") Integer id) throws Exception {
        return new ResponseEntity(modelMapper.map(itemPoService.findItemPoById(id),ItemPoAppDto.class),HttpStatus.OK);
    }

    @GetMapping("/app/inventory/{id}")
    public ResponseEntity findItemPoInventoryForApp (@PathVariable (value = "id") Integer id) throws Exception {
        return new ResponseEntity(itemPoService.findItemPoInventoryForApp(id), HttpStatus.OK);
    }
}
