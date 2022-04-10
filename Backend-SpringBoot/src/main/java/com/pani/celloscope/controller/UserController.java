package com.pani.celloscope.controller;

import com.pani.celloscope.model.ApiResponse;
import com.pani.celloscope.model.User;
import com.pani.celloscope.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(value = "*", maxAge = 3600)
public class UserController {
    final UserRepository userRepository;

    ApiResponse res = ApiResponse.getInstance();

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody User user) {

        boolean exists = userRepository.existsById(user.getUserId());
        if (exists) {
            res.getData().put("data", userRepository.findById(user.getUserId()));
            res.setStatusCode(HttpStatus.CONFLICT.value());
            res.setMessage("User ID already in use !");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(res);
        }

        try {
            res.getData().put("data", userRepository.save(user));
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("User registration succesfull !");
            return ResponseEntity.status(HttpStatus.OK).body(res);

        } catch (Exception e) {
            res.getData().put("data", null);
            res.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            res.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }

    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {

        boolean exists = userRepository.existsById(user.getUserId());
        if (exists) {
            res.getData().put("data", userRepository.save(user));
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("User information updated !");
            return ResponseEntity.status(HttpStatus.OK).body(res);
        } else {
            res.getData().put("data", null);
            res.setStatusCode(HttpStatus.NOT_FOUND.value());
            res.setMessage("User not found !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }


    }


}


