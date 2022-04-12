package com.pani.celloscope.controller;

import com.pani.celloscope.model.ApiResponse;
import com.pani.celloscope.model.User;
import com.pani.celloscope.repository.UserRepository;
import com.pani.celloscope.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
public class LoginController {
    Logger logger = LoggerFactory.getLogger(LoginController.class);
    final LoginService service;
    final UserRepository userRepository;

    ApiResponse apiResponse = ApiResponse.getInstance();

    @Autowired
    public LoginController(LoginService service, UserRepository userRepository) {
        this.service = service;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        logger.info("login: starting with " + user);
        apiResponse = service.login(user);

        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);

    }


    @PostMapping("/forgot")
    public ResponseEntity<?> forgot(@RequestBody User user) {
        logger.info("forgot: starting with " + user.getUserId());
        apiResponse = service.forgot(user);
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
}
