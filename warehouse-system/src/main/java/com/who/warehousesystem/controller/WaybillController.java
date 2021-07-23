package com.who.warehousesystem.controller;

import com.who.warehousesystem.dto.WbDetailsDto;
import com.who.warehousesystem.dto.WbDetailsSaveDto;
import com.who.warehousesystem.dto.WbDgvDto;
import com.who.warehousesystem.dto.WbSaveDto;
import com.who.warehousesystem.service.WaybillService;
import org.hibernate.validator.constraints.pl.REGON;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/waybills")
public class WaybillController {

    @Autowired
    WaybillService waybillService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("")
    public ResponseEntity findAllWaybillsDgv () {
        return new ResponseEntity(waybillService.findAllWaybillsDgv().stream().map(wb ->
                modelMapper.map(wb, WbDgvDto.class)), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity saveWaybill (@RequestBody WbSaveDto dto,
                                       @RequestHeader (value = "userId") Integer userId) throws Exception {
        return new ResponseEntity(modelMapper.map(waybillService.saveWaybill(dto, userId), WbDgvDto.class),
                HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity editWaybill (@PathVariable (value = "id") Integer id,@RequestBody WbSaveDto dto,
                                       @RequestHeader (value = "userId") Integer userId) throws Exception {
        return new ResponseEntity(modelMapper.map(waybillService.editWaybill(id,dto,userId), WbDgvDto.class),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteWaybill (@PathVariable (value = "id") Integer id,
                                         @RequestHeader (value = "userId") Integer userId) throws Exception {
        waybillService.deleteWaybill(id,userId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity searchWaybill (@RequestParam (value = "wbNo") String wbNo,
                                         @RequestParam (value = "wbDate") String wbDate,
                                         @RequestParam (value = "boxes") String boxes,
                                         @RequestParam (value = "pallets") String pallets,
                                         @RequestParam (value = "cityId") String cityId,
                                         @RequestParam (value = "districtId") String districtId,
                                         @RequestParam (value = "centerId") String centerId,
                                         @RequestParam (value = "d") boolean d) {
        return new ResponseEntity(waybillService.searchWaybill(wbNo,wbDate,boxes,pallets,cityId,districtId,
                centerId,d).stream().map(wb -> (modelMapper.map(wb,WbDgvDto.class))), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity findWaybillDetailsById (@PathVariable (value = "id") Integer id) throws Exception {
        return new ResponseEntity(modelMapper.map(waybillService.findWaybillById(id), WbDetailsDto.class), HttpStatus.OK);
    }

    @GetMapping("/dp-items/{id}")
    public ResponseEntity findDpItemsByWb (@PathVariable (value = "id") Integer id) throws Exception {
        return new ResponseEntity(waybillService.findDpItemsByWb(id), HttpStatus.OK);
    }

    @GetMapping("/wb-items/{id}")
    public ResponseEntity findWbItemsByWb (@PathVariable (value = "id") Integer id) throws Exception {
        return new ResponseEntity(waybillService.findWbItemsByWb(id), HttpStatus.OK);
    }

    @PostMapping("/details/{id}")
    public ResponseEntity saveWbDetails (@PathVariable (value = "id") Integer id, @RequestBody WbDetailsSaveDto dto,
                                         @RequestHeader (value = "userId") Integer userId) throws Exception {
        waybillService.saveWbDetails(id,dto,userId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
