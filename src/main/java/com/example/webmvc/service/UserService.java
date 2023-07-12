package com.example.webmvc.service;

import com.example.webmvc.entity.User;
import com.example.webmvc.exception.DaoException;
import com.example.webmvc.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    boolean isLoginAvailable(String login) throws ServiceException;
    boolean authenticate(String login, String password) throws ServiceException;
    Optional<User> register(User user) throws ServiceException;

    Optional<User> findUserByName(String username) throws ServiceException;

    Optional<User> findUserByLogin(String login) throws ServiceException;
    public abstract List<User> findAll() throws ServiceException;



}
