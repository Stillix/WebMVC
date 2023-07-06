package com.example.webmvc.dao;

import com.example.webmvc.exception.DaoException;

public interface UserDao {
    boolean authenticate(String login, String password) throws DaoException;


}
