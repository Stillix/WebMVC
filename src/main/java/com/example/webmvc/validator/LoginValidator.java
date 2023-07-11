package com.example.webmvc.validator;

public interface LoginValidator {
    boolean isValidLogin(String login);
    boolean isValidPassword(String password);

}
