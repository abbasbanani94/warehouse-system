package com.who.warehousesystem.controller;

import com.who.warehousesystem.dto.ItemDisposalDgvDto;
import com.who.warehousesystem.dto.ItemDisposalSaveDto;
import com.who.warehousesystem.dto.KitDisposalDgvDto;
import com.who.warehousesystem.dto.KitDisposalSaveDto;
import com.who.warehousesystem.service.KitDisposalService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kitDisposals")
public class KitDisposalController {

    @Autowired
    KitDisposalService kitDisposalService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/{disposalId}")
    public ResponseEntity findAllKitDisposalsByDisposal (@PathVariable (value = "disposalId") Integer disposalId) {
        return new ResponseEntity(kitDisposalService.findAllKitDisposalsByDisposal(disposalId).stream()
                .map(kitDisposal -> (modelMapper.map(kitDisposal, KitDisposalDgvDto.class))), HttpStatus.OK);
    }

    @PostMapping("/{disposalId}")
    public ResponseEntity saveKitDisposal (@PathVariable (value = "disposalId") Integer disposalId,
                                           @RequestBody KitDisposalSaveDto dto,
                                           @RequestHeader (value = "userId") Integer userId) throws Exception {
        return new ResponseEntity(modelMapper.map(kitDisposalService.saveKitDisposal(disposalId,dto,userId),
                KitDisposalDgvDto.class), HttpStatus.OK);
    }

    @PutMapping("/{disposalId}/{id}")
    public ResponseEntity editItemDisposal (@PathVariable (value = "disposalId") Integer disposalId,
                                            @PathVariable (value = "id") Integer id,
                                            @RequestBody KitDisposalSaveDto dto,
                                            @RequestHeader (value = "userId") Integer userId) throws Exception {
        return new ResponseEntity(modelMapper.map(kitDisposalService.editKitDisposal(id,disposalId,dto,userId),
                KitDisposalDgvDto.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteKitDisposal (@PathVariable (value = "id") Integer id,
                                              @RequestHeader (value = "userId") Integer userId) throws Exception {
        kitDisposalService.deleteKitDisposal(id,userId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity searchKitDisposals (@RequestParam (value = "disposalId") Integer disposalId,
                                               @RequestParam (value = "poId") String poId,
                                               @RequestParam (value = "kitPoId") String kitPoId,
                                               @RequestParam (value = "qty") String qty) {
        return new ResponseEntity(kitDisposalService.searchKitDisposals(disposalId,poId,kitPoId,qty).stream()
                .map(kitDisposal -> (modelMapper.map(kitDisposal,KitDisposalDgvDto.class))), HttpStatus.OK);
    }
}
