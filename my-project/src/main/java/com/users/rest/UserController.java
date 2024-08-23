package com.users.rest;

import com.users.entity.UserEntity;
import com.users.repository.UserRepository;
import com.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/my-project")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @GetMapping(value="/user/{userId}/get")
    public ResponseEntity<UserEntity> get(@PathVariable("userId") Long userId) {

        ResponseEntity<UserEntity> response = userService.findByUserId(userId);
        return response;
    }

    @PostMapping(value="/create")
    public ResponseEntity createUser(@RequestBody UserEntity user) {
        ResponseEntity response = userService.createUser(user);
        return response;
    }

    @PostMapping(value="/user/{userId}/update")
    public ResponseEntity updateUser(
            @PathVariable("userId") Long userId,
            @RequestBody UserEntity user) {
        ResponseEntity response = userService.updateUser(user,userId);
        return response;
    }
}
