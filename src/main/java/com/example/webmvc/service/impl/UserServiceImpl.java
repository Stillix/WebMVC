package com.example.webmvc.service.impl;

import com.example.webmvc.dao.impl.UserDaoImpl;
import com.example.webmvc.service.UserService;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl instance = new UserServiceImpl();

    private UserServiceImpl() {
    }

    public static UserService getInstance() {
        return instance;
    }

    @Override
    public boolean authenticate(String login, String password) {
        //валидация логина и пароля + шифрование md5
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        boolean match = userDao.authenticate(login, password);
        return match;
    }
}
