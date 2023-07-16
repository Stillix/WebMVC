package com.example.webmvc.command.impl;

import com.example.webmvc.command.Command;
import com.example.webmvc.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public class FindNoticeByPersonNameCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        return null;
    }
}
