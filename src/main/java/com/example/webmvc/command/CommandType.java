package com.example.webmvc.command;

import com.example.webmvc.command.impl.*;

public enum CommandType {
    REGISTER(new RegisterCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    DEFAULT(new DefaultCommand()),
    SHOW_USERS(new ShowUserCommand()),
    FIND_USER_BY_NAME(new FindUserByNameCommand()),
    DELETE_USER_BY_ID(new DeleteUserCommand()),
    DELETE_NOTICE(new DeleteNoticeCommand()),
    FIND_NOTICE_BY_PERSON_NAME(new FindNoticeByPersonNameCommand()),
    FIND_NOTICE_BY_USER_NAME(new FindNoticeByUserNameCommand()),
    SHOW_ALL_NOTICES(new ShowAllNoticesCommand()),
    CREATE_NOTICE(new CreateNoticeCommand());


    Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public static Command defineCommand(String commandStr) {
        Command command;
        if (commandStr != null) {
            try {
                CommandType currentCommand = CommandType.valueOf(commandStr.toUpperCase());
                command = currentCommand.command;
            } catch (IllegalArgumentException e) {
                command = DEFAULT.command;
            }
        } else {
            command = DEFAULT.command;
        }
        return command;
    }
}
