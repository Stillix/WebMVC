package com.example.webmvc.command;

import com.example.webmvc.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface Command {
 String execute(HttpServletRequest request) throws CommandException;

}
