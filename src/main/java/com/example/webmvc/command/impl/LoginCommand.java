package com.example.webmvc.command.impl;

import com.example.webmvc.command.Command;
import com.example.webmvc.entity.User;
import com.example.webmvc.exception.CommandException;
import com.example.webmvc.exception.ServiceException;
import com.example.webmvc.service.UserService;
import com.example.webmvc.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

import static com.example.webmvc.command.Message.FAILED_LOGIN_MSG;
import static com.example.webmvc.command.RequestAttributeName.FAILED_LOGIN;
import static com.example.webmvc.command.RequestAttributeName.USER;
import static com.example.webmvc.command.RequestParameterName.LOGIN;
import static com.example.webmvc.command.RequestParameterName.PASSWORD;
import static com.example.webmvc.command.SessionAttributeName.CURRENT_PAGE;
import static com.example.webmvc.command.SessionAttributeName.USER_ROLE;

public class LoginCommand implements Command {




    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        UserService userService = UserServiceImpl.getInstance();
        String page;
        HttpSession session = request.getSession();
        try {
            if (userService.authenticate(login, password)) {
                Optional<User> user = userService.findUserByLogin(login);
                session.setAttribute(USER_ROLE, user.get().getUserRole());
                page = "/pages/profile.jsp";
            } else {
                request.setAttribute(FAILED_LOGIN, FAILED_LOGIN_MSG);
                page = "/pages/authorization.jsp";
            }
            session.setAttribute(CURRENT_PAGE, page);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
