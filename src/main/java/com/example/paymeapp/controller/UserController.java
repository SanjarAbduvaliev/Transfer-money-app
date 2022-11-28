package com.example.paymeapp.controller;

import com.example.paymeapp.payload.UserDto;
import com.example.paymeapp.repository.UserRepository;
import com.example.paymeapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {


    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseEntity<?> regesterUser(@Valid @RequestBody UserDto userDto){
        return userService.addUser(userDto);
    }
    @PutMapping("/edit")
    public ResponseEntity<?> editUser(@Valid@RequestBody UserDto userDto){
        return userService.editUser(userDto);
    }
    @GetMapping
    public ResponseEntity<?> getAll(){
        return userService.getUsers();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getId(@PathVariable Integer id){
        return userService.getUserId(id);
    }
}
