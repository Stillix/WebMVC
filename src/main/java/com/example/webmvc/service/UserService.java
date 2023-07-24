package com.example.webmvc.service;

import com.example.webmvc.entity.User;
import com.example.webmvc.exception.ServiceException;

import java.util.List;

public interface UserService {

    boolean authenticate(String login, String password) throws ServiceException;

    User register(User user) throws ServiceException;
    User findUserByLogin(String login) throws ServiceException;

    List<User> findUserByName(String username) throws ServiceException;

    List<User> findAll() throws ServiceException;

    boolean deleteUser(int userId) throws ServiceException;

    boolean updateUser(User user) throws ServiceException;


}
