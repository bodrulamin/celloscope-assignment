package com.pani.celloscope.validation;

import com.pani.celloscope.model.User;

public class UserValidation {
    public static boolean isValidMobile(User user) {

        return user.getMobile().matches("[0-9+]+");
    }

    public static boolean isValidPassword(User user) {
        return user.getPassword() != null;
    }

    public static boolean isUserNotNull(User user) {
        return user != null;
    }

    public static boolean isPasswordMatched(String password, String password1) {
        return password.equals(password1);
    }

}

