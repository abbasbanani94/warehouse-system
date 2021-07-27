package com.who.warehousesystem.controller;

import com.who.warehousesystem.dto.*;
import com.who.warehousesystem.model.User;
import com.who.warehousesystem.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("")
    public ResponseEntity findAllUsers () {
        return new ResponseEntity(userService.findAllUsers().stream().map(user -> (
                modelMapper.map(user, UserDgvDto.class))), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity saveUser (@RequestBody UserSaveDto dto) throws Exception {
        return new ResponseEntity(modelMapper.map(userService.saveUser(dto), UserDgvDto.class), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity editUser (@PathVariable (value = "id") Integer id,
                                    @RequestBody UserSaveDto dto) throws Exception {
        return new ResponseEntity(modelMapper.map(userService.editUser(id,dto),UserDgvDto.class), HttpStatus.OK);
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity activateUser (@PathVariable (value = "id") Integer id) throws Exception {
        return new ResponseEntity(modelMapper.map(userService.activateUser(id), UserDgvDto.class), HttpStatus.OK);
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity deactivateUser (@PathVariable (value = "id") Integer id) throws Exception {
        return new ResponseEntity(modelMapper.map(userService.deactivateUser(id), UserDgvDto.class), HttpStatus.OK);
    }

    @PutMapping("/password")
    public ResponseEntity changePassword (@RequestBody PasswordDto dto) throws Exception {
        return new ResponseEntity(modelMapper.map(userService.changePassword(dto),UserDgvDto.class), HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity login (@RequestParam (value = "username") String username,
                                 @RequestParam (value = "password") String password) throws Exception {
        return new ResponseEntity(modelMapper.map(userService.login(new LoginDto(username,password)), UserLoginDto.class), HttpStatus.OK);
    }
}
