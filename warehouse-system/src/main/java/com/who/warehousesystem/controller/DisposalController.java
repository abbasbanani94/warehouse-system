package com.who.warehousesystem.controller;

import com.who.warehousesystem.dto.DisposalDgvDto;
import com.who.warehousesystem.dto.DisposalSaveDto;
import com.who.warehousesystem.model.Disposal;
import com.who.warehousesystem.service.DisposalService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/disposals")
public class DisposalController {

    @Autowired
    DisposalService disposalService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("")
    public ResponseEntity findAllDisposalDgv () {
        return new ResponseEntity(disposalService.findAllDisposalDgv().stream().map(
                disposal -> (modelMapper.map(disposal, DisposalDgvDto.class))), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity saveDisposal (@RequestBody DisposalSaveDto dto,
                                        @RequestHeader (value = "userId") Integer userId) throws Exception {
        return new ResponseEntity(modelMapper.map(disposalService.saveDisposal(dto,userId),DisposalDgvDto.class),
                HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity editDisposal (@PathVariable (value = "id") Integer id,
                                        @RequestBody DisposalSaveDto dto,
                                        @RequestHeader (value = "userId") Integer userId) throws Exception {
        return new ResponseEntity(modelMapper.map(disposalService.editDisposal(id,dto,userId), DisposalDgvDto.class),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDisposal (@PathVariable (value = "id") Integer id,
                                          @RequestHeader (value = "userId") Integer userId) throws Exception {
        disposalService.deleteDisposal(id,userId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity searchDisposals (@RequestParam (value = "reason") String reason,
                                           @RequestParam (value = "date") String date,
                                           @RequestParam (value = "d") boolean d) {
        return new ResponseEntity(disposalService.searchDisposals(reason,date,d).stream().map(
                disposal -> (modelMapper.map(disposal,DisposalDgvDto.class))), HttpStatus.OK);
    }

    @GetMapping("/details/{id}")
    public ResponseEntity findDisposalDetailsById (@PathVariable (value = "id") Integer id) throws Exception {
        return new ResponseEntity(modelMapper.map(disposalService.findDisposalById(id), DisposalSaveDto.class),
                HttpStatus.OK);
    }
}
