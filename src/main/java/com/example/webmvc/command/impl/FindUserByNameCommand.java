package com.example.webmvc.command.impl;

import com.example.webmvc.command.Command;
import com.example.webmvc.entity.User;
import com.example.webmvc.exception.CommandException;
import com.example.webmvc.exception.ServiceException;
import com.example.webmvc.service.UserService;
import com.example.webmvc.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.webmvc.command.RequestAttributeName.MESSAGE;
import static com.example.webmvc.command.RequestAttributeName.USER_LIST;
import static com.example.webmvc.command.RequestParameterName.NAME;

public class FindUserByNameCommand implements Command {
    private static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();
        String userName = request.getParameter(NAME);
        try {
            List<User> userList = userService.findUserByName(userName);
            if (!userList.isEmpty()) {
                request.setAttribute(USER_LIST, userList);
                request.setAttribute(MESSAGE, "Users found: " + userList.size());
                logger.info("Users found: " + userList.size());
            } else {
                logger.info("No users found");
                request.setAttribute(MESSAGE, "No users found");
            }
        } catch (ServiceException e) {
            logger.error("Failed to find users by name: " + e.getMessage());
            throw new CommandException("Failed to find users by name");
        }
        return "/pages/users.jsp";
    }
}
