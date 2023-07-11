package com.example.webmvc.validator.impl;

import com.example.webmvc.validator.LoginValidator;

public class LoginValidatorImpl implements LoginValidator {
    @Override
    public boolean isValidLogin(String login) {
        return login != null && login.matches("^[a-zA-Z0-9]{4,20}$");
    }

    @Override
    public boolean isValidPassword(String password) {
        return password != null && password.matches("^(?=.*[0-9])(?=.*[a-zA-Z]).{8,20}$");
    }
}
