package com.pani.celloscope.service;

import com.pani.celloscope.controller.UserController;
import com.pani.celloscope.model.ApiResponse;
import com.pani.celloscope.model.User;
import com.pani.celloscope.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    Logger logger = LoggerFactory.getLogger(UserService.class);
    ApiResponse res = ApiResponse.getInstance();
    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> registerUser(User user) {

            boolean exists = userRepository.existsById(user.getUserId());
            if (exists) {
                res.getData().put("data", userRepository.findById(user.getUserId()));
                res.setStatusCode(HttpStatus.CONFLICT.value());
                res.setMessage("User ID already in use ! ");
                logger.error("register: "+ res.getMessage() + user);
                return ResponseEntity.status(HttpStatus.CONFLICT).body(res);
            }

            try {
                res.getData().put("data", userRepository.save(user));
                res.setStatusCode(HttpStatus.OK.value());
                res.setMessage("User registration succesful !");
                logger.info("register: "+ res.getMessage() + user);
                return ResponseEntity.status(HttpStatus.OK).body(res);

            } catch (Exception e) {
                res.getData().put("data", null);
                res.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
                res.setMessage(e.getMessage());
                logger.error("register: "+ res.getMessage() + " " + user);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
            }
        }


    public ResponseEntity<?> updateUser(User user) {
        boolean exists = userRepository.existsById(user.getUserId());

        if (exists) {
            res.getData().put("data", userRepository.save(user));
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("User information updated ! ");
            logger.info("update: " + res.getMessage() + user);
            return ResponseEntity.status(HttpStatus.OK).body(res);
        } else {
            res.getData().put("data", null);
            res.setStatusCode(HttpStatus.NOT_FOUND.value());
            res.setMessage("User not found ! ");
            logger.error("update: " + res.getMessage() + user);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }

    }
}
