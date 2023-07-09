package com.example.webmvc.service.impl;

import com.example.webmvc.dao.UserDao;
import com.example.webmvc.dao.impl.UserDaoImpl;
import com.example.webmvc.entity.User;
import com.example.webmvc.exception.DaoException;
import com.example.webmvc.exception.ServiceException;
import com.example.webmvc.service.UserService;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl instance = new UserServiceImpl();

    private UserServiceImpl() {
    }

    public static UserService getInstance() {
        return instance;
    }
    public boolean isLoginAvailable(String login) throws ServiceException {
        try {
            UserDao userDao = UserDaoImpl.getInstance();
            Optional<User> existingUser = userDao.getUserByLogin(login);
            return !existingUser.isPresent();
        } catch (DaoException e) {
            throw new ServiceException("Failed to check login availability", e);
        }
    }


    @Override
    public boolean authenticate(String login, String password) throws ServiceException {
        //валидация логина и пароля
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        boolean match = false;
        try {
            match = userDao.authenticate(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return match;
    }
}
