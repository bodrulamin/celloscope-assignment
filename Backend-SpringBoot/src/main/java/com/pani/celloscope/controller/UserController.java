package com.pani.celloscope.controller;

import com.pani.celloscope.model.ApiResponse;
import com.pani.celloscope.model.User;
import com.pani.celloscope.repository.UserRepository;
import com.pani.celloscope.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(value = "*", maxAge = 3600)
public class UserController {
    final UserService service;
    Logger logger = LoggerFactory.getLogger(UserController.class);
    ApiResponse apiResponse = ApiResponse.getInstance();

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        logger.info("register: starting with " + user);
        apiResponse = service.registerUser(user);
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);

    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        logger.info("update: starting with " + user);
        apiResponse = service.updateUser(user);
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }


}


