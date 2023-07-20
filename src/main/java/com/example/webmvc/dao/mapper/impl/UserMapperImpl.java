package com.example.webmvc.dao.mapper.impl;

import com.example.webmvc.entity.User;
import com.example.webmvc.dao.mapper.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserMapperImpl implements Mapper<User> {
    private static final String ID_USER = "id_user";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String PHONE = "phone";
    private static final String EMAIL = "email";
    private static final String ROLE = "role";

    @Override
    public User buildEntity(ResultSet resultSet) throws SQLException {
        int userId = resultSet.getInt(ID_USER);
        String userLogin = resultSet.getString(LOGIN);
        String userName = resultSet.getString(NAME);
        String userSurname = resultSet.getString(SURNAME);
        String userPhone = resultSet.getString(PHONE);
        String userEmail = resultSet.getString(EMAIL);
        String userRole = resultSet.getString(ROLE);
        return User.newBuilder()
                .setUserId(userId)
                .setUserLogin(userLogin)
                .setUserName(userName)
                .setUserSurname(userSurname)
                .setUserPhone(userPhone)
                .setUserEmail(userEmail)
                .setUserRole(userRole)
                .build();
    }
}

