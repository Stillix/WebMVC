package com.example.webmvc.command;

import jakarta.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface Command {
 String execute(HttpServletRequest request);

}
