package com.example.webmvc.validator.impl;

import com.example.webmvc.validator.LoginValidator;

public class LoginValidatorImpl implements LoginValidator {
    public static final String LOGIN_REGEX = "^[a-zA-Z0-9]{4,20}$";
    public static final String PASSWORD_REGEX = "(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d]{6,30}";

    @Override
    public boolean isValidLogin(String login) {
        return login != null && login.matches(LOGIN_REGEX);
    }

    @Override
    public boolean isValidPassword(String password) {
        return password != null && password.matches(PASSWORD_REGEX);
    }
}
