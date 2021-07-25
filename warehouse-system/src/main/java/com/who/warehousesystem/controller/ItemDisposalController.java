package com.who.warehousesystem.controller;

import com.who.warehousesystem.dto.ItemDisposalDgvDto;
import com.who.warehousesystem.dto.ItemDisposalSaveDto;
import com.who.warehousesystem.service.ItemDisposalService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/itemDisposals")
public class ItemDisposalController {

    @Autowired
    ItemDisposalService itemDisposalService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/{disposalId}")
    public ResponseEntity findAllItemDisposalsByDisposalId (@PathVariable (value = "disposalId") Integer disposalId) {
        return new ResponseEntity(itemDisposalService.findAllItemDisposalsByDisposal(disposalId).stream()
                .map(itemDisposal -> (modelMapper.map(itemDisposal, ItemDisposalDgvDto.class))),HttpStatus.OK);
    }

    @PostMapping("/{disposalId}")
    public ResponseEntity saveItemDisposal (@PathVariable (value = "disposalId") Integer disposalId,
                                            @RequestBody ItemDisposalSaveDto dto,
                                            @RequestHeader (value = "userId") Integer userId) throws Exception {
        return new ResponseEntity(modelMapper.map(itemDisposalService.saveItemDisposal(disposalId,dto,userId),
                ItemDisposalDgvDto.class), HttpStatus.OK);
    }

    @PutMapping("/{disposalId}/{id}")
    public ResponseEntity editItemDisposal (@PathVariable (value = "disposalId") Integer disposalId,
                                            @PathVariable (value = "id") Integer id,
                                            @RequestBody ItemDisposalSaveDto dto,
                                            @RequestHeader (value = "userId") Integer userId) {
        return new ResponseEntity(itemDisposalService.editItemDisposal(id,disposalId,dto,userId), HttpStatus.OK);
    }
}
