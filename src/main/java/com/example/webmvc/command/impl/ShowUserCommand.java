package com.example.webmvc.command.impl;

import com.example.webmvc.command.Command;
import com.example.webmvc.entity.User;
import com.example.webmvc.exception.CommandException;
import com.example.webmvc.exception.ServiceException;
import com.example.webmvc.service.UserService;
import com.example.webmvc.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import static com.example.webmvc.command.RequestAttributeName.USER_LIST;

public class ShowUserCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();
        try {
            List<User> userList = userService.findAll();
            request.setAttribute(USER_LIST, userList);
        } catch (ServiceException e) {
            throw new CommandException(e);

        }
        return "/pages/users.jsp";
    }
}
