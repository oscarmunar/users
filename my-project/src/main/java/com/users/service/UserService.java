package com.users.service;

import com.users.entity.UserEntity;
import com.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;



    public ResponseEntity<UserEntity> findByUserId(Long userId) {
        Optional<UserEntity> userOpt = Optional.ofNullable(userRepository.findByUserId(userId));

        if(userOpt.isPresent()) {
            return ResponseEntity.ok(userOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<UserEntity> createUser(UserEntity user) {
        userRepository.saveUser(user);

        Optional<UserEntity> userOpt = Optional.ofNullable(userRepository.findByUserId(user.getUserId()));
        if(userOpt.isPresent()) {
            return ResponseEntity.ok(userOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<UserEntity> updateUser(UserEntity user, Long idUser) {
        userRepository.updateUser(user,idUser);

        Optional<UserEntity> userOpt = Optional.ofNullable(userRepository.findByUserId(idUser));
        if(userOpt.isPresent()) {
            return ResponseEntity.ok(userOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public String helloService() {
        return "Service ::::::: My-project User works!!!!! " + userRepository.findById(1L) + " :::::::::";
    }

}
