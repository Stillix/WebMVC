package com.example.webmvc.service.impl;

import com.example.webmvc.dao.UserDao;
import com.example.webmvc.dao.impl.UserDaoImpl;
import com.example.webmvc.entity.User;
import com.example.webmvc.exception.DaoException;
import com.example.webmvc.exception.ServiceException;
import com.example.webmvc.service.UserService;
import com.example.webmvc.util.PasswordEncoder;
import com.example.webmvc.util.Validation;
import com.example.webmvc.validator.impl.UserValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static Logger logger = LogManager.getLogger();
    private static UserServiceImpl instance = new UserServiceImpl();
    private UserDaoImpl userDao = UserDaoImpl.getInstance();
    private static UserValidatorImpl userValidator = new UserValidatorImpl();
    private static PasswordEncoder passwordEncoder = new PasswordEncoder();

    private UserServiceImpl() {
    }

    public static UserService getInstance() {
        return instance;
    }

    @Override
    public boolean authenticate(String login, String password) throws ServiceException {
        boolean match = false;
        try {
            if (userValidator.isValidLogin(login) && userValidator.isValidPassword(password)) {
                String digest = passwordEncoder.encode(password);
                match = userDao.authenticate(login, digest);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return match;
    }


    @Override
    public Optional<User> register(User user) throws ServiceException {
        Map<String, String> validUser = userValidator.isValidUser(user);
        if (validUser.isEmpty()) {
            String digest = passwordEncoder.encode(user.getUserPassword());
            user.setUserPassword(digest);
            try {
                userDao.create(user);
            } catch (DaoException e) {
                throw new ServiceException("Failed to register user" + e);
            }
            logger.info("Password after encode: " + user.getUserPassword());
            return Optional.of(user);
        } else {
            return getUser(validUser);
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

    private static Optional<User> getUser(Map<String, String> errorMessage) {
        User errorUser = User.newBuilder().setErrorMessage(errorMessage).build();
        return Optional.of(errorUser);
    }

    @Override
    public List<User> findUserByName(String username) throws ServiceException {
        try {
            List<User> userList = userDao.findUserByName(username);
            return userList;
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

    @Override
    public boolean deleteUser(int userId) throws ServiceException {
        try {
            return (userDao.delete(userId));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateUser(User user) throws ServiceException {
        try {
            return userDao.update(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
