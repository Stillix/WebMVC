package com.example.webmvc.dao;

import com.example.webmvc.entity.User;
import com.example.webmvc.exception.DaoException;

import java.util.List;

public interface UserDao {
    boolean authenticate(String login, String password) throws DaoException;

    List<User> findUserByName(String username) throws DaoException;

    User findUserByLogin(String login) throws DaoException;

}
