package com.example.webmvc.mapper.impl;

import com.example.webmvc.entity.User;
import com.example.webmvc.mapper.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserMapperImpl implements Mapper<User> {

    @Override
    public User buildObj(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            int userId = resultSet.getInt(1);
            String userLogin = resultSet.getString(2);
            String userPassword = resultSet.getString(3);
            String userName = resultSet.getString(4);
            String userSurname = resultSet.getString(5);
            String userPhone = resultSet.getString(6);
            String userEmail = resultSet.getString(7);
            int userRoleId = resultSet.getInt(8);
            return User.newBuilder()
                    .setUserId(userId)
                    .setUserLogin(userLogin)
                    .setUserPassword(userPassword)
                    .setUserName(userName)
                    .setUserSurname(userSurname)
                    .setUserPhone(userPhone)
                    .setUserEmail(userEmail)
                    .setUserRoleId(userRoleId)
                    .build();
        }
        return null;
    }
}

