package com.example.webmvc.service.impl;

import com.example.webmvc.service.UserService;

public class UserServiceImpl implements UserService {
    private static UserService instance = new UserServiceImpl();

    private UserServiceImpl() {
    }

    public static UserService getInstance() {
        return instance;
    }

    @Override
    public boolean authenticate(String login, String password) {
        return login.equals(password); //todo
    }
}
