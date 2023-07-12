package com.example.webmvc.dao;

import com.example.webmvc.entity.User;
import com.example.webmvc.exception.DaoException;

import java.util.Optional;

public interface UserDao {
    boolean authenticate(String login, String password) throws DaoException;

    Optional<User> findUserById(int id) throws DaoException;

    Optional<User> findUserByName(String username) throws DaoException;

    Optional<User> findUserByLogin(String login) throws DaoException;

}
