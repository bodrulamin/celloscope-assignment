package com.pani.celloscope;

import com.pani.celloscope.model.User;
import com.pani.celloscope.validation.UserValidation;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CelloscopeApplicationTests {

    @Test
    void UserHasValidMobile() {
        User user = new User(9999, "password", "01725717136");
        assertTrue(UserValidation.isValidMobile(user));
    }

    @Test
    void UserPasswordShouldNotBeNull() {
        User user = new User(9999, "password", "01725717136");
        assertTrue(UserValidation.isPasswordNotNull(user));
    }

    @Test
    void UserShouldNotBeNull() {
        User user = new User(9999, "password", "01725717136");
       // user= null;
        assertTrue(UserValidation.isUserNotNull(user));
    }

    @Test
    void PasswordMatcherShouldBeValid() {
        User user = new User(9999, "lskdjf", "01725717136");
        User user2 = new User(9999, "lskdjf", "01725717136");

        assertTrue(UserValidation.isPasswordMatched(user.getPassword(),user2.getPassword()));
    }

    @Test
    void MobileMatcherShouldBeValid() {
        User user = new User(9999, "lskdjf", "01725717136");
        User user2 = new User(9999, "lskdjf", "01725717136");
        assertTrue(UserValidation.isMobileMatched(user.getMobile(),user2.getMobile()));
    }
    @Test
    void UserInformationShouldBeValid() {
        User user = new User(9999, "lskdjf", "01725717136");
         assertTrue(UserValidation.userIsValid(user));
    }



    @Test
    void contextLoads() {
    }


}
