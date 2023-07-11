package com.example.webmvc.command.impl;

import com.example.webmvc.command.Command;
import jakarta.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return  "pages/authorization.jsp";
    }
}
