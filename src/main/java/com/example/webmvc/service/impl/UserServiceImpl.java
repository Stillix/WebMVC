package com.example.webmvc.service.impl;

import com.example.webmvc.dao.UserDao;
import com.example.webmvc.dao.impl.UserDaoImpl;
import com.example.webmvc.entity.User;
import com.example.webmvc.exception.DaoException;
import com.example.webmvc.exception.ServiceException;
import com.example.webmvc.service.UserService;
import com.example.webmvc.validator.impl.LoginValidatorImpl;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static Logger logger = LogManager.getLogger();
    private static UserServiceImpl instance = new UserServiceImpl();
    private static  UserDaoImpl userDao = UserDaoImpl.getInstance();
    private static  LoginValidatorImpl loginValidator = new LoginValidatorImpl();

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
                String digest = DigestUtils.md5Hex(password);
                match = userDao.authenticate(login, digest);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return match;
    }


    @Override
    public Optional<User> register(User user) throws ServiceException {
        if (isLoginAvailable(user.getUserLogin())) {
            if (loginValidator.isValidLogin(user.getUserLogin()) && loginValidator.isValidPassword(user.getUserPassword())) {
                String digest = DigestUtils.md5Hex(user.getUserPassword());
                user.setUserPassword(digest);
                try {
                    userDao.create(user);
                } catch (DaoException e) {
                    throw new ServiceException("Failed to register user" + e);
                }
                logger.info("Пароль шифрованный: " + user.getUserPassword());
                return Optional.of(user);
            } else {
                return getUser("Invalid login or password format");
            }
        } else {
            return getUser("Login is already exist");
        }
    }

    private static Optional<User> getUser(String errorMessage) {
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
        boolean match;
        try {
            if (userDao.delete(userId)) {
                match = true;
            } else {
                match = false;
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return match;
    }

    @Override
    public boolean updateUser(User user) throws ServiceException {
        boolean match;
        try {
            if (userDao.update(user)) {
                match = true;
            } else {
                match = false;
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return match;
    }
}
