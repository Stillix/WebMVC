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


import static com.example.webmvc.command.RequestAttributeName.MESSAGE;
import static com.example.webmvc.command.RequestParameterName.USER_ID;

public class DeleteUserCommand implements Command {
    private static Logger logger = LogManager.getLogger();
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String userIdString = request.getParameter(USER_ID);
        try {
            int userId = Integer.parseInt(userIdString);
            boolean deleted = userService.deleteUser(userId);
            if (deleted) {
                request.setAttribute(MESSAGE, "User with ID " + userId + " has been deleted");
            } else {
                request.setAttribute(MESSAGE, "Failed to delete user with ID " + userId);
            }
        } catch (ServiceException e) {
            logger.error("Failed to delete user");
            throw new CommandException("Failed to delete user" + e.getMessage());
        }
        return "/pages/users.jsp";
    }
}
