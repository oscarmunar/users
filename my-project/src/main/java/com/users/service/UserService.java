package com.users.service;

import com.users.entity.UserEntity;
import com.users.repository.UserRepository;
import com.users.response.CreateResponse;
import com.users.response.UpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;



    public ResponseEntity findByUserId(Long userId) {

        Optional<UserEntity> userOpt = Optional.ofNullable(userRepository.findByUserId(userId));

        if(userOpt.isPresent()) {
            return ResponseEntity.ok(userOpt.get());
        } else {
            return new ResponseEntity<>("User Not found.", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity createUser(UserEntity user) {

        CreateResponse response = new CreateResponse();

        Optional<Long> idUserCreated = Optional.ofNullable(userRepository.saveUser(user));

        if(idUserCreated.isPresent()) {
            response.setId(idUserCreated.get().toString());
            response.setSucceeded(Boolean.TRUE);

        } else {
            response.setId("");
            response.setSucceeded(Boolean.FALSE);
        }
        return ResponseEntity.ok(response);
    }

    public ResponseEntity updateUser(UserEntity user, Long idUser) {

        UpdateResponse response = new UpdateResponse();

        Optional<Boolean> userUpdated = Optional.ofNullable(userRepository.updateUser(user,idUser));

        if(userUpdated.isPresent()) {
            response.setSucceeded(Boolean.TRUE);
        } else {
            response.setSucceeded(Boolean.FALSE);
        }

        return ResponseEntity.ok(response);
    }

    public String helloService() {
        return "Service ::::::: My-project User works!!!!! " + userRepository.findById(1L) + " :::::::::";
    }

}
