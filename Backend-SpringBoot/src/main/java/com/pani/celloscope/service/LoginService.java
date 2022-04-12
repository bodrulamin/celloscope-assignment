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

import java.util.Optional;

import static com.pani.celloscope.validation.UserValidation.*;

@Service
public class LoginService {
    Logger logger = LoggerFactory.getLogger(LoginService.class);
    final UserRepository userRepository;
    ApiResponse apiResponse = ApiResponse.getInstance();

    @Autowired
    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ApiResponse login(User user) {
        boolean validated = UserValidation.isPasswordNotNull(user);
        if (validated) {
            return proceedToLogin(user);
        } else {
            apiResponse.getData().put("data", null);
            apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
            apiResponse.setMessage("Request data is not valid or sufficient");
            return apiResponse;

        }

    }

    private ApiResponse proceedToLogin(User user) {
        Optional<User> dbUser = userRepository.findById(user.getUserId());

        if (dbUser.isEmpty()) {
            apiResponse.getData().put("data", null);
            apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
            apiResponse.setMessage("User not found ! ");
            logger.error("login: " + apiResponse.getMessage() + user);
            return apiResponse;

        } else {
            boolean passwordMatched = isPasswordMatched(user.getPassword(), dbUser.get().getPassword());
            if (passwordMatched) {
                apiResponse.getData().put("data", dbUser.get());
                apiResponse.setStatusCode(HttpStatus.OK.value());
                apiResponse.setMessage("Login Successful ! ");
                logger.info("login: " + apiResponse.getMessage() + user);
                return apiResponse;
            } else {
                apiResponse.getData().put("data", null);
                apiResponse.setStatusCode(HttpStatus.UNAUTHORIZED.value());
                apiResponse.setMessage("User ID  password does not match ");
                logger.error("login: " + apiResponse.getMessage() + user);
                return apiResponse;
            }
        }
    }


    public ApiResponse forgot(User user) {

        Optional<User> dbUser = userRepository.findById(user.getUserId());

        if (dbUser.isEmpty()) {
            apiResponse.getData().put("data", null);
            apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
            apiResponse.setMessage("User not found ! ");
            logger.error("forgot: " + apiResponse.getMessage() + user);
            return apiResponse;

        } else {

            boolean mobileMatched = isMobileMatched(dbUser.get().getMobile(), user.getMobile());

            if (mobileMatched) {
                apiResponse.getData().put("data", dbUser.get());
                apiResponse.setStatusCode(HttpStatus.OK.value());
                apiResponse.setMessage("User ID mobile matched ");
                logger.info("forgot: " + apiResponse.getMessage() + user);
                return apiResponse;
            } else {
                apiResponse.getData().put("data", null);
                apiResponse.setStatusCode(HttpStatus.UNAUTHORIZED.value());
                apiResponse.setMessage("User ID  mobile does not match ");
                logger.error("forgot: " + apiResponse.getMessage() + user);
                return apiResponse;
            }

        }
    }
}
