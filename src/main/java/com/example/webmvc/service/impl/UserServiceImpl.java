package com.example.webmvc.service.impl;

import com.example.webmvc.dao.UserDao;
import com.example.webmvc.dao.impl.UserDaoImpl;
import com.example.webmvc.entity.User;
import com.example.webmvc.exception.DaoException;
import com.example.webmvc.exception.ServiceException;
import com.example.webmvc.service.UserService;
import com.example.webmvc.validator.impl.LoginValidatorImpl;


import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl instance = new UserServiceImpl();
    UserDaoImpl userDao = UserDaoImpl.getInstance();
    LoginValidatorImpl loginValidator = new LoginValidatorImpl();

    private UserServiceImpl() {
    }

    public static UserService getInstance() {
        return instance;
    }

    public boolean isLoginAvailable(String login) throws ServiceException {
        try {
            UserDao userDao = UserDaoImpl.getInstance();
            Optional<User> existingUser = userDao.findUserByLogin(login);
            return !existingUser.isPresent();
        } catch (DaoException e) {
            throw new ServiceException("Failed to check login availability", e);
        }
    }

    @Override
    public boolean authenticate(String login, String password) throws ServiceException {
        boolean match = false;
        try {
            if (loginValidator.isValidLogin(login) && loginValidator.isValidPassword(password)) {
                match = userDao.authenticate(login, password);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return match;
    }

    @Override
    public Optional<User> register(User user) throws ServiceException {
        try {
            if (loginValidator.isValidLogin(user.getUserLogin()) && loginValidator.isValidPassword(user.getUserPassword())) {
                userDao.create(user);
            } else {
                return Optional.empty();
            }
            return Optional.of(user);
        } catch (DaoException e) {
            throw new ServiceException("User creation error", e);
        }
    }

    @Override
    public Optional<User> findUserByName(String username) throws ServiceException {
        try {
            return userDao.findUserByName(username);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<User> findUserByLogin(String login) throws ServiceException {
        try {
            return userDao.findUserByLogin(login);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findAll() throws ServiceException {
        try {
            return userDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
