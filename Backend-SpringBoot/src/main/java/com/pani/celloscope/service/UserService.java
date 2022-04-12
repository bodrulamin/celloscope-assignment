package com.pani.celloscope.service;

import com.pani.celloscope.model.ApiResponse;
import com.pani.celloscope.model.User;
import com.pani.celloscope.repository.UserRepository;
import com.pani.celloscope.validation.UserValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    Logger logger = LoggerFactory.getLogger(UserService.class);
    ApiResponse apiResponse = ApiResponse.getInstance();
    final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ApiResponse registerUser(User user) {
        boolean validated = UserValidation.userIsValid(user);
        if (validated) {
            return proceedToRegisterUser(user);
        } else {
            apiResponse.getData().put("data", null);
            apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
            apiResponse.setMessage("Request data is not valid or sufficient");
            return apiResponse;

        }

     }

    private ApiResponse proceedToRegisterUser(User user) {
        boolean exists = userRepository.existsById(user.getUserId());
        if (exists) {
            apiResponse.getData().put("data", userRepository.findById(user.getUserId()));
            apiResponse.setStatusCode(HttpStatus.CONFLICT.value());
            apiResponse.setMessage("User ID already in use ! ");
            logger.error("register: " + apiResponse.getMessage() + user);
            return apiResponse;
        }

        try {
            apiResponse.getData().put("data", userRepository.save(user));
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setMessage("User registration succesful !");
            logger.info("register: " + apiResponse.getMessage() + user);
            return apiResponse;
        } catch (Exception e) {
            apiResponse.getData().put("data", null);
            apiResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            apiResponse.setMessage(e.getMessage());
            logger.error("register: " + apiResponse.getMessage() + " " + user);
            return apiResponse;        }
    }


    public ApiResponse updateUser(User user) {
        boolean validated = UserValidation.isValidMobile(user);

         if (validated) {
            return proceedToUpdateUser(user);
        } else {
            apiResponse.getData().put("data", null);
            apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
            apiResponse.setMessage("Request data is not valid or sufficient");
            return apiResponse;

        }


    }

    private ApiResponse proceedToUpdateUser(User user) {
        boolean exists = userRepository.existsById(user.getUserId());
        if (exists) {
            apiResponse.getData().put("data", userRepository.save(user));
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setMessage("User information updated ! ");
            logger.info("update: " + apiResponse.getMessage() + user);
            return apiResponse;
        } else {
            apiResponse.getData().put("data", null);
            apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
            apiResponse.setMessage("User not found ! ");
            logger.error("update: " + apiResponse.getMessage() + user);
            return apiResponse;
        }
    }
}
