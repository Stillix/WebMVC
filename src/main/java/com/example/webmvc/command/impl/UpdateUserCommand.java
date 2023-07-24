package com.example.webmvc.command.impl;

import com.example.webmvc.command.Command;
import com.example.webmvc.entity.User;
import com.example.webmvc.exception.CommandException;
import com.example.webmvc.exception.ServiceException;
import com.example.webmvc.service.UserService;
import com.example.webmvc.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.Optional;

import static com.example.webmvc.command.RequestAttributeName.MESSAGE;
import static com.example.webmvc.command.RequestParameterName.*;
import static com.example.webmvc.command.RequestParameterName.EMAIL;
import static com.example.webmvc.command.SessionAttributeName.USER;
import static com.example.webmvc.command.SessionAttributeName.USER_ID;

public class UpdateUserCommand implements Command {

    private static Logger logger = LogManager.getLogger();
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        request.setAttribute(USER, user);
        String userName = request.getParameter(NAME);
        String userSurname = request.getParameter(SURNAME);
        String userPhone = request.getParameter(PHONE);
        String userEmail = request.getParameter(EMAIL);
        int userId = (int) session.getAttribute(USER_ID);
        logger.log(Level.INFO, "Executing updating Command");
        user.setUserName(userName);
        user.setUserSurname(userSurname);
        user.setUserPhone(userPhone);
        user.setUserEmail(userEmail);
        user.setUserId(userId);
        try {
            boolean updatedUser = userService.updateUser(user);
            if (updatedUser) {
                logger.log(Level.INFO, "User was successfully update : ");
                request.setAttribute(MESSAGE, "User was successfully update");
                return "/pages/change_user_info.jsp";
            } else {
                request.setAttribute(MESSAGE, "User not update  ");
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Except");
            throw new CommandException(e);
        }
        return "/pages/change_user_info.jsp";
    }
}
