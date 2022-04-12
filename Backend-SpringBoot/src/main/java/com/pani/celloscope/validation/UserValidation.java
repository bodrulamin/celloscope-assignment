package com.pani.celloscope.validation;

import com.pani.celloscope.model.User;

public class UserValidation {
    public static boolean isValidMobile(User user) {
        if (user == null || user.getMobile() == null) return false;
        return user.getMobile().matches("[0-9+]+");
    }

    public static boolean isPasswordNotNull(User user) {
        if (user == null ) return false;
        return user.getPassword() != null;
    }

    public static boolean isUserNotNull(User user) {
        return user != null;
    }

    public static boolean isPasswordMatched(String password, String password1) {
        if (password == null || password1 == null) return false;
        return password.equals(password1);
    }

    public static boolean isMobileMatched(String mobile, String mobile2) {
        if (mobile == null || mobile2 == null) return false;

        return mobile.equals(mobile2);
    }

    public static boolean userIsValid(User user) {
        return isUserNotNull(user) &&
                isValidMobile(user) &&
                isPasswordNotNull(user);
    }
}

