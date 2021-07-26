package com.who.warehousesystem.controller;

import com.who.warehousesystem.dto.WorkerDgvDto;
import com.who.warehousesystem.dto.WorkerSaveDto;
import com.who.warehousesystem.model.Worker;
import com.who.warehousesystem.service.WorkerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/workers")
public class WorkerController {

    @Autowired
    WorkerService workerService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("")
    public ResponseEntity findAllWorkersDgv () {
        return new ResponseEntity(workerService.findAllWorkersDgv().stream().map(worker -> (
                modelMapper.map(worker, WorkerDgvDto.class))), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity saveWorker (@RequestBody WorkerSaveDto dto,
                                      @RequestHeader (value = "userId") Integer userId) throws Exception {
        return new ResponseEntity(modelMapper.map(workerService.saveWorker(dto,userId),WorkerDgvDto.class),
                HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity editWorker (@PathVariable (value = "id") Integer id,
                                      @RequestBody WorkerSaveDto dto,
                                      @RequestHeader (value = "userId") Integer userId) throws Exception {
        return new ResponseEntity(workerService.editWorker(id,dto,userId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteWorker (@PathVariable (value = "id") Integer id,
                                        @RequestHeader (value = "userId") Integer userId) throws Exception {
        workerService.deleteWorker(id,userId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity searchWorkers (@RequestParam (value = "enName") String enName,
                                         @RequestParam (value = "arName") String arName,
                                         @RequestParam (value = "mobile") String mobile) {
        return new ResponseEntity(workerService.searchWorkers(enName,arName,mobile).stream().map(
                worker -> (modelMapper.map(worker, WorkerDgvDto.class))), HttpStatus.OK);
    }
}
