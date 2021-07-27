package com.who.warehousesystem.controller;

import com.who.warehousesystem.dto.CheckDetailsDto;
import com.who.warehousesystem.dto.CheckSaveDto;
import com.who.warehousesystem.dto.CheckWorkerDto;
import com.who.warehousesystem.model.Check;
import com.who.warehousesystem.model.CheckWorker;
import com.who.warehousesystem.service.CheckService;
import org.intellij.lang.annotations.JdkConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

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

    @GetMapping("/details/{id}")
    public ResponseEntity findCheckDetailsById (@PathVariable (value = "id") Integer id) throws Exception {
        Check check = checkService.findCheckById(id);
        return new ResponseEntity(new CheckDetailsDto(check.getId(),check.getCheckType().getEnName(),
                Date.valueOf(check.getCheckDate()),check.getNotes()), HttpStatus.OK);
    }

    @GetMapping("/{id}/all-workers")
    public ResponseEntity findAllWorkers (@PathVariable (value = "id") Integer id) {
        return new ResponseEntity(checkService.findAllWorkers(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/check-workers")
    public ResponseEntity findCheckWorkers (@PathVariable (value = "id") Integer id) {
        return new ResponseEntity(checkService.findCheckWorkers(id), HttpStatus.OK);
    }

    @PostMapping("/{id}/check-workers")
    public ResponseEntity saveCheckWorkers (@PathVariable (value = "id") Integer id,
                                            @RequestBody CheckWorkerDto dto,
                                            @RequestHeader (value = "userId") Integer userId) throws Exception {
        return new ResponseEntity(checkService.saveCheckWorkers(id,dto,userId), HttpStatus.OK);
    }
}
