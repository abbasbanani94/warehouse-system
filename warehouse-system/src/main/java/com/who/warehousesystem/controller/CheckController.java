package com.who.warehousesystem.controller;

import com.who.warehousesystem.dto.CheckSaveDto;
import com.who.warehousesystem.service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/checks")
public class CheckController {

    @Autowired
    CheckService checkService;

    @GetMapping("")
    public ResponseEntity findAllChecksDgv () {
        return new ResponseEntity(checkService.findAllChecksDgv(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity saveCheck (@RequestBody CheckSaveDto dto,
                                     @RequestHeader (value = "userId") Integer userId) throws Exception {
        return new ResponseEntity(checkService.saveCheck(dto,userId), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity editCheck (@PathVariable (value = "id") Integer id,
                                     @RequestBody CheckSaveDto dto,
                                     @RequestHeader (value = "userId") Integer userId) throws Exception{
        return new ResponseEntity(checkService.editCheck(id,dto,userId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCheck (@PathVariable (value = "id") Integer id,
                                       @RequestHeader (value = "userId") Integer userId) throws Exception {
        checkService.deleteCheck(id,userId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity searchChecks (@RequestParam (value = "type") String type,
                                        @RequestParam (value = "notes") String notes,
                                        @RequestParam (value = "date") String date,
                                        @RequestParam (value = "d") boolean d) {
        return new ResponseEntity(checkService.searchChecks(type,notes,date,d), HttpStatus.OK);
    }
}
