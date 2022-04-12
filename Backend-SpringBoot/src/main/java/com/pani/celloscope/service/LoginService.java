package com.pani.celloscope.service;

import com.pani.celloscope.controller.LoginController;
import com.pani.celloscope.model.ApiResponse;
import com.pani.celloscope.model.User;
import com.pani.celloscope.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.pani.celloscope.validation.UserValidation.isPasswordMatched;

@Service
public class LoginService {
    Logger logger = LoggerFactory.getLogger(LoginService.class);

    final UserRepository userRepository;
    ApiResponse res = ApiResponse.getInstance();

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> login(User user) {
        Optional<User> dbUser = userRepository.findById(user.getUserId());

        if (dbUser.isEmpty()) {
            res.getData().put("data", null);
            res.setStatusCode(HttpStatus.NOT_FOUND.value());
            res.setMessage("User not found ! ");
            logger.error("login: " + res.getMessage() + user);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);

        } else {

            boolean passwordMatched = isPasswordMatched(user.getPassword(), dbUser.get().getPassword());

            if (passwordMatched) {
                res.getData().put("data", dbUser.get());
                res.setStatusCode(HttpStatus.OK.value());
                res.setMessage("Login Successful ! ");
                logger.info("login: " + res.getMessage() + user);
                return ResponseEntity.status(HttpStatus.OK).body(res);
            } else {
                res.getData().put("data", null);
                res.setStatusCode(HttpStatus.UNAUTHORIZED.value());
                res.setMessage("User ID  password does not match ");
                logger.error("login: " + res.getMessage() + user);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
            }

        }
    }





    public ResponseEntity<?> forgot(User user) {

        Optional<User> dbUser = userRepository.findById(user.getUserId());

        if (dbUser.isEmpty()) {
            res.getData().put("data", null);
            res.setStatusCode(HttpStatus.NOT_FOUND.value());
            res.setMessage("User not found ! ");
            logger.error("forgot: " + res.getMessage() + user);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);

        } else {

            boolean mobileMatched = dbUser.get().getMobile().equals(user.getMobile());

            if (mobileMatched) {
                res.getData().put("data", dbUser.get());
                res.setStatusCode(HttpStatus.OK.value());
                res.setMessage("User ID mobile matched ");
                logger.info("forgot: " + res.getMessage() + user);
                return ResponseEntity.status(HttpStatus.OK).body(res);
            } else {
                res.getData().put("data", null);
                res.setStatusCode(HttpStatus.UNAUTHORIZED.value());
                res.setMessage("User ID  mobile does not match ");
                logger.error("forgot: " + res.getMessage() + user);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
            }

        }
    }
}
