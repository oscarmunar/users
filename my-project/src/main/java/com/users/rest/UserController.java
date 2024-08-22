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
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
        ResponseEntity<UserEntity> response = userService.createUser(user);
        return response;
    }

    @PostMapping(value="/user/{userId}/update")
    public ResponseEntity<UserEntity> updateUser(
            @PathVariable("userId") Long userId,
            @RequestBody UserEntity user) {
        ResponseEntity<UserEntity> response = userService.updateUser(user,userId);
        return response;
    }

    private String SerializeJson(Object myObject) { return ""; }

    private Object DeserializeJson (String json) { return new Object(); }



    @GetMapping("hello-my-project")
    public String hello(){

        UserEntity newUser = new UserEntity();
        newUser.setUserId(7L);
        newUser.setFirstName("nombre7");
        newUser.setLastName("apellido7");
        newUser.setEmailAddress("valor7@correo.com");

        // userRepository.save(newUser);

        //userRepository.findAll();
        //userRepository.findByUserId(3L);
        //userService.saveUser(newUser);
        //userRepository.updateUser(newUser,7L);


        return "My-project User works!!!!!";
    }
}
