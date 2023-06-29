package com.example.webmvc.command.impl;

import com.example.webmvc.command.Command;
import com.example.webmvc.service.UserService;
import com.example.webmvc.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        UserService userService = UserServiceImpl.getInstance();
        String page;
        if(userService.authenticate(login,password)){
            request.setAttribute("user",login);
            page = "pages/home.jsp";
        }
        else {
            request.setAttribute("login_msg","incorrect login or password");
            page = "index.jsp";
        }

        return page;
    }
}
