package com.pani.celloscope.controller;

import com.pani.celloscope.model.ApiResponse;
import com.pani.celloscope.model.User;
import com.pani.celloscope.repository.UserRepository;
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
    final UserRepository userRepository;

    ApiResponse res = ApiResponse.getInstance();

    @Autowired
    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        logger.info("login: starting with " + user );
        Optional<User> dbUser = userRepository.findById(user.getUserId());

        if (dbUser.isEmpty()) {
            res.getData().put("data", null);
            res.setStatusCode(HttpStatus.NOT_FOUND.value());
            res.setMessage("User not found ! ");
            logger.error("login: " + res.getMessage() + user );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);

        } else {

            boolean passwordMatched = dbUser.get().getPassword().equals(user.getPassword());

            if (passwordMatched) {
                res.getData().put("data", dbUser.get());
                res.setStatusCode(HttpStatus.OK.value());
                res.setMessage("Login Successful ! ");
                logger.info("login: "+ res.getMessage() + user );
                return ResponseEntity.status(HttpStatus.OK).body(res);
            } else {
                res.getData().put("data", null);
                res.setStatusCode(HttpStatus.UNAUTHORIZED.value());
                res.setMessage("User ID  password does not match ");
                logger.error("login: "+ res.getMessage() + user );
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
            }

        }
    }

    @PostMapping("/forgot")
    public ResponseEntity<?> forgot(@RequestBody User user) {
        logger.info("forgot: starting with " + user.getUserId() );
        Optional<User> dbUser = userRepository.findById(user.getUserId());

        if (dbUser.isEmpty()) {
            res.getData().put("data", null);
            res.setStatusCode(HttpStatus.NOT_FOUND.value());
            res.setMessage("User not found ! ");
            logger.error("forgot: "+ res.getMessage() + user );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);

        } else {

            boolean mobileMatched = dbUser.get().getMobile().equals(user.getMobile());

            if (mobileMatched) {
                res.getData().put("data", dbUser.get());
                res.setStatusCode(HttpStatus.OK.value());
                res.setMessage("User ID mobile matched ");
                logger.info("forgot: "+ res.getMessage() + user );
                return ResponseEntity.status(HttpStatus.OK).body(res);
            } else {
                res.getData().put("data", null);
                res.setStatusCode(HttpStatus.UNAUTHORIZED.value());
                res.setMessage("User ID  mobile does not match ");
                logger.error("forgot: "+ res.getMessage() + user );
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
            }

        }
    }
}
