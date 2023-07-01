package com.example.webmvc.command.impl;

import com.example.webmvc.command.Command;
import jakarta.servlet.http.HttpServletRequest;

public class DefaultCommand implements Command {
    private static final String mainPath = "home.jsp";
    @Override
    public String execute(HttpServletRequest request) {
        return mainPath;
    }
}
