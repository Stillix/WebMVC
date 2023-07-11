package com.example.webmvc.command.impl;

import com.example.webmvc.command.Command;
import com.example.webmvc.dao.UserDao;
import com.example.webmvc.dao.impl.UserDaoImpl;
import com.example.webmvc.entity.User;
import com.example.webmvc.exception.CommandException;
import com.example.webmvc.exception.DaoException;
import com.example.webmvc.exception.ServiceException;
import com.example.webmvc.service.UserService;
import com.example.webmvc.service.impl.UserServiceImpl;
import com.example.webmvc.validator.impl.LoginValidatorImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static com.example.webmvc.command.RequestAttributeName.ERROR_MESSAGE;
import static com.example.webmvc.command.RequestAttributeName.USER;
import static com.example.webmvc.command.RequestParameterName.*;

public class AddUserCommand implements Command {

    private static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String name = request.getParameter(NAME);
        String surname = request.getParameter(SURNAME);
        String phone = request.getParameter(PHONE);
        String email = request.getParameter(EMAIL);

        UserService userService = UserServiceImpl.getInstance();
        UserDaoImpl userDao = UserDaoImpl.getInstance();

        LoginValidatorImpl loginValidator = new LoginValidatorImpl();
        try {
            if (userService.isLoginAvailable(login)) {
                if (loginValidator.isValidLogin(login) && loginValidator.isValidPassword(password)) {
                    User user = new User(0, login, password, name, surname, phone, email, 1);
                    Optional<User> createdUser = userDao.create(user);
                    logger.log(Level.INFO, "User was successfully created and added to the database" + user);
                    if (createdUser.isPresent()) {
                        return "/pages/success_register.jsp";
                    } else {
                        request.setAttribute(ERROR_MESSAGE, "Failed to create user");
                        logger.log(Level.ERROR, "Failed to create user");
                    }
                } else {                                                        //pass 8-20+minimum 1 uppercase letter+minimum 1 number
                    request.setAttribute(ERROR_MESSAGE, "Invalid login or password format");//login 4-20 symbols letters and numbers
                    logger.log(Level.WARN, "Invalid login or password format");
                }
            } else {
                request.setAttribute(ERROR_MESSAGE, "Login is already exist");
                logger.log(Level.INFO, "Login is already exist");
            }
        } catch (DaoException | ServiceException e) {
            throw new RuntimeException("Failed to execute AddUserCommand", e);
        }
        return "/pages/registration.jsp";
    }
}

