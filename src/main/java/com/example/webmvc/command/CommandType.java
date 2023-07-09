package com.example.webmvc.command;

import com.example.webmvc.command.impl.AddUserCommand;
import com.example.webmvc.command.impl.DefaultCommand;
import com.example.webmvc.command.impl.LoginCommand;
import com.example.webmvc.command.impl.LogoutCommand;

public enum CommandType {
    ADD_USER(new AddUserCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    DEFAULT(new DefaultCommand());

    Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public static Command defineCommand(String commandStr) {
        Command command;

        CommandType currentCommand = CommandType.valueOf(commandStr.toUpperCase());
        return currentCommand.command;
    }
}
