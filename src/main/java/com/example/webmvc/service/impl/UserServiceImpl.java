package com.example.webmvc.service.impl;

import com.example.webmvc.dao.UserDao;
import com.example.webmvc.dao.impl.UserDaoImpl;
import com.example.webmvc.entity.User;
import com.example.webmvc.exception.DaoException;
import com.example.webmvc.exception.ServiceException;
import com.example.webmvc.service.UserService;
import com.example.webmvc.util.PasswordEncoder;
import com.example.webmvc.validator.impl.UserValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.List;
import java.util.Map;

import static com.example.webmvc.command.RequestAttributeName.ERROR_LOGIN_EXIST_MESSAGE;

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

    public User isLoginExist(String login) throws ServiceException {
        try {
            UserDao userDao = UserDaoImpl.getInstance();
            User existingUser = userDao.findUserByLogin(login);
            return existingUser;
        } catch (DaoException e) {
            throw new ServiceException("Failed to check login availability", e);
        }
    }

    @Override
    public User register(User user) throws ServiceException {
        Map<String, String> validUser = userValidator.validate(user);
        if (isLoginExist(user.getUserLogin()) != null) {
            validUser.put(ERROR_LOGIN_EXIST_MESSAGE, "Login is already exist");
            return getUser(validUser);
        }
        if (validUser.isEmpty()) {
            String digest = passwordEncoder.encode(user.getUserPassword());
            user.setUserPassword(digest);
            try {
                userDao.create(user);
            } catch (DaoException e) {
                throw new ServiceException("Failed to register user" + e);
            }
            logger.info("Password after encode: " + user.getUserPassword());
            return user;
        } else {
            return getUser(validUser);
        }
    }

    @Override
    public User findUserByLogin(String login) throws ServiceException {
        try {
            return userDao.findUserByLogin(login);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    private static User getUser(Map<String, String> errorMessage) {
        User errorUser = User.newBuilder().setErrorMessage(errorMessage).build();
        return errorUser;
    }

    @Override
    public boolean updateUser(User user) throws ServiceException {
        boolean validUser = userValidator.isValidUser(user);
        if (validUser) {
            try {
                return userDao.update(user);
            } catch (DaoException e) {
                throw new ServiceException("Failed to update user" + e);
            }
        } else {
            return false;
        }
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
}
