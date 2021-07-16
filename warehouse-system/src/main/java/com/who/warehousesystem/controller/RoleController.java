package com.who.warehousesystem.controller;

import com.who.warehousesystem.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping("")
    public ResponseEntity findAllRoles () {
        return new ResponseEntity(roleService.findAllRoles(), HttpStatus.OK);
    }
}
