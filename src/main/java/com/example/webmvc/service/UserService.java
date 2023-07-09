package com.example.webmvc.service;

import com.example.webmvc.exception.ServiceException;

public interface UserService {

    boolean authenticate(String login, String password) throws ServiceException;


    boolean isLoginAvailable(String login) throws ServiceException;
}
