package com.example.webmvc.command.impl;

import com.example.webmvc.command.Command;
import com.example.webmvc.service.UserService;
import com.example.webmvc.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import static com.example.webmvc.util.Message.FAILED_LOGIN_MSG;
import static com.example.webmvc.util.RequestAttributeName.FAILED_LOGIN;
import static com.example.webmvc.util.RequestAttributeName.USER;
import static com.example.webmvc.util.RequestParameterName.LOGIN;
import static com.example.webmvc.util.RequestParameterName.PASSWORD;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        UserService userService = UserServiceImpl.getInstance();
        String page;
        if(userService.authenticate(login,password)){
            request.setAttribute(USER,login);
            page = "pages/profile.jsp";
        }
        else {
            request.setAttribute(FAILED_LOGIN,FAILED_LOGIN_MSG);
            page = "index.jsp";
        }

        return page;
    }
}
