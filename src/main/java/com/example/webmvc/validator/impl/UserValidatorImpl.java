package com.example.webmvc.validator.impl;

import com.example.webmvc.dao.UserDao;
import com.example.webmvc.dao.impl.UserDaoImpl;
import com.example.webmvc.entity.User;
import com.example.webmvc.exception.DaoException;
import com.example.webmvc.exception.ServiceException;
import com.example.webmvc.validator.UserValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.example.webmvc.command.RequestAttributeName.*;

public class UserValidatorImpl implements UserValidator {
    public static final String LOGIN_REGEX = "^[a-zA-Z0-9]{4,20}$";
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
    public static final String NAME_REGEX = "^[a-zA-Z]{2,15}$";
    public static final String SURNAME_REGEX = "^[a-zA-Z]{2,30}$";
    public static final String PHONE_REGEX = "^\\s*\\+?375((25|29|33|44)\\d{7})\\s*$";
    public static final String PASSWORD_REGEX = "(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d]{6,30}";


    public Map<String, String> isValidUser(User user) throws ServiceException {
        Map<String, String> errorMessages = new HashMap<>();
        if (isLoginExist(user.getUserLogin())) {
            errorMessages.put(ERROR_LOGIN_EXIST_MESSAGE, "Login is already exist");
        } else {
            if (!isValidLogin(user.getUserLogin())) {
                errorMessages.put(ERROR_LOGIN_MESSAGE, "Invalid login format");
            }
        }
        if (!isValidPassword(user.getUserPassword())) {
            errorMessages.put(ERROR_PASSWORD_MESSAGE, "Invalid password format");
        }
        if (!isValidEmail(user.getUserEmail())) {
            errorMessages.put(ERROR_EMAIL_MESSAGE, "Invalid email format");
        }
        if (!isValidName(user.getUserName())) {
            errorMessages.put(ERROR_NAME_MESSAGE, "Invalid name format");
        }
        if (!isValidSurname(user.getUserSurname())) {
            errorMessages.put(ERROR_SURNAME_MESSAGE, "Invalid surname format");
        }
        if (!isValidPhone(user.getUserPhone())) {
            errorMessages.put(ERROR_PHONE_MESSAGE, "Invalid phone format");
        }

        return errorMessages;
    }

    public boolean isLoginExist(String login) throws ServiceException {
        try {
            UserDao userDao = UserDaoImpl.getInstance();
            Optional<User> existingUser = userDao.findUserByLogin(login);
            return existingUser.isPresent();
        } catch (DaoException e) {
            throw new ServiceException("Failed to check login availability", e);
        }
    }

    @Override
    public boolean isValidLogin(String login) {
        return login != null && login.matches(LOGIN_REGEX);
    }

    @Override
    public boolean isValidPassword(String password) {
        return password != null && password.matches(PASSWORD_REGEX);
    }

    public boolean isValidEmail(String email) {
        return email != null && email.matches(EMAIL_REGEX);
    }

    public boolean isValidName(String name) {
        return name != null && name.matches(NAME_REGEX);
    }

    public boolean isValidSurname(String surname) {
        return surname != null && surname.matches(SURNAME_REGEX);
    }

    public boolean isValidPhone(String phone) {
        return phone != null && phone.matches(PHONE_REGEX);
    }
}
