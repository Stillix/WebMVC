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

import java.util.Map;
import java.util.Optional;

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
        try {
            Optional<User> createdUser = userService.register(user);
            if (createdUser.isPresent()) {
                Map<String, String> errorMessage = createdUser.get().getErrorMessages();
                if (errorMessage == null || errorMessage.isEmpty()) {
                    logger.log(Level.INFO, "User was successfully created and added to the database: " + createdUser);
                    return "/pages/success_register.jsp";
                } else {
                    for (Map.Entry<String, String> error : errorMessage.entrySet()) {
                        request.setAttribute(error.getKey(), error.getValue());
                    }
                }
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Except");
            throw new CommandException(e);
        }
        return "/pages/registration.jsp";
    }
}

