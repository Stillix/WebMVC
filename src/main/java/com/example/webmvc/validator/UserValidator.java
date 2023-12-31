package com.example.webmvc.validator;

import com.example.webmvc.entity.User;
import com.example.webmvc.exception.ServiceException;

import java.util.Map;

public interface UserValidator {

    boolean isValidUser(User user);

    boolean isValidLogin(String login);

    boolean isValidPassword(String password);

    Map<String, String> validate(User user) throws ServiceException;

}
