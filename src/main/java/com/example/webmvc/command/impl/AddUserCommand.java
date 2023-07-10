package com.example.webmvc.command.impl;

import com.example.webmvc.command.Command;
import com.example.webmvc.dao.UserDao;
import com.example.webmvc.dao.impl.UserDaoImpl;
import com.example.webmvc.entity.User;
import com.example.webmvc.exception.CommandException;
import com.example.webmvc.exception.DaoException;
import com.example.webmvc.exception.ServiceException;
import com.example.webmvc.service.UserService;
import com.example.webmvc.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

import static com.example.webmvc.command.RequestAttributeName.USER;
import static com.example.webmvc.command.RequestParameterName.*;

public class AddUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String name = request.getParameter(NAME);
        String surname = request.getParameter(SURNAME);
        String phone = request.getParameter(PHONE);
        String email = request.getParameter(EMAIL);

        UserService userService = UserServiceImpl.getInstance();
        UserDaoImpl userDao = UserDaoImpl.getInstance();

        try {

            if (userService.isLoginAvailable(login)) {
                User user = new User(0, login, password, name, surname, phone, email, 1);
                Optional<User> createdUser = userDao.create(user);
                if (createdUser.isPresent()) {
                    return "/pages/success_register.jsp";
                } else {
                    request.setAttribute("errorMessage", "Failed to create user");
                }
            } else {
                request.setAttribute("errorMessage", "Username is already taken");
            }
        } catch (DaoException | ServiceException e) {
            throw new RuntimeException("Failed to execute AddUserCommand", e);
        }

        return "index.jsp";
    }
}

