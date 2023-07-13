package com.example.webmvc.command.impl;

import com.example.webmvc.command.Command;
import com.example.webmvc.entity.User;
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
import static com.example.webmvc.command.RequestParameterName.*;

public class RegisterCommand implements Command {

    private static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String userLogin = request.getParameter(LOGIN);
        String userPassword = request.getParameter(PASSWORD);
        String userName = request.getParameter(NAME);
        String userSurname = request.getParameter(SURNAME);
        String userPhone = request.getParameter(PHONE);
        String userEmail = request.getParameter(EMAIL);

        UserService userService = UserServiceImpl.getInstance();
        LoginValidatorImpl loginValidator = new LoginValidatorImpl();

        try {
            if (userService.isLoginAvailable(userLogin)) {
                if (loginValidator.isValidLogin(userLogin) && loginValidator.isValidPassword(userPassword)) {
                    User user = User.newBuilder()
                            .setUserId(0)
                            .setUserLogin(userLogin)
                            .setUserPassword(userPassword)
                            .setUserName(userName)
                            .setUserSurname(userSurname)
                            .setUserPhone(userPhone)
                            .setUserEmail(userEmail)
                            .setUserRole("user")
                            .build();
                    Optional<User> createdUser = userService.register(user);
                    if (createdUser.isPresent()) {
                        logger.log(Level.INFO, "User was successfully created and added to the database" + user);
                        return "/pages/success_register.jsp";
                    } else {
                        request.setAttribute(ERROR_MESSAGE, "Failed to create user");
                        logger.log(Level.ERROR, "Failed to create user");
                        return "/pages/registration.jsp";
                    }
                } else {                                                        //pass 6-30+minimum 1 uppercase letter+minimum 1 number
                    request.setAttribute(ERROR_MESSAGE, "Invalid login or password format");//login 4-20 symbols letters and numbers
                    logger.log(Level.WARN, "Invalid login or password format");
                }
            } else {
                request.setAttribute(ERROR_MESSAGE, "Login is already exist");
                logger.log(Level.INFO, "Login is already exist");
            }
        } catch (ServiceException e) {
            throw new RuntimeException("Failed to execute AddUserCommand", e);
        }
        return "/pages/registration.jsp";
    }
}

