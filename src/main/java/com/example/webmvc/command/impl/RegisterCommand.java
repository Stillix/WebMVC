package com.example.webmvc.command.impl;

import com.example.webmvc.command.Command;
import com.example.webmvc.entity.User;
import com.example.webmvc.exception.CommandException;
import com.example.webmvc.exception.DaoException;
import com.example.webmvc.exception.ServiceException;
import com.example.webmvc.service.UserService;
import com.example.webmvc.service.impl.UserServiceImpl;
import com.mysql.cj.util.StringUtils;
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
    public String execute(HttpServletRequest request) throws CommandException {
        int userId = 0;
        String userLogin = request.getParameter(LOGIN);
        String userPassword = request.getParameter(PASSWORD);
        String userName = request.getParameter(NAME);
        String userSurname = request.getParameter(SURNAME);
        String userPhone = request.getParameter(PHONE);
        String userEmail = request.getParameter(EMAIL);
        UserService userService = UserServiceImpl.getInstance();
        logger.log(Level.INFO, "Executing RegisterCommand");
        try {
            User user = User.newBuilder()
                    .setUserId(userId)
                    .setUserLogin(userLogin)
                    .setUserPassword(userPassword)
                    .setUserName(userName)
                    .setUserSurname(userSurname)
                    .setUserPhone(userPhone)
                    .setUserEmail(userEmail)
                    .setUserRole("client")
                    .build();
            Optional<User> createdUser = userService.register(user);
            if (createdUser.isPresent()) {
                String errorMessage = createdUser.get().getErrorMessage();
                if (errorMessage == null || errorMessage.isEmpty()) {
                    logger.log(Level.INFO, "User was successfully created and added to the database: " + user);
                    return "/pages/success_register.jsp";
                } else {
                    request.setAttribute(ERROR_MESSAGE, errorMessage);
                    logger.log(Level.WARN, errorMessage);
                }
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Except");
            throw new CommandException(e);
        }
        return "/pages/registration.jsp";
    }

}

