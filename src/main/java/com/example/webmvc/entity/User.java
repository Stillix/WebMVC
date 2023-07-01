package com.example.webmvc.entity;

import java.util.StringJoiner;

public class User extends AbstractEntity {
    private int userId;
    private String userName;
    private String userLogin;
    private String userEmail;

    public User(int userId, String userName, String userLogin, String userEmail) {
        this.userId = userId;
        this.userName = userName;
        this.userLogin = userLogin;
        this.userEmail = userEmail;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("userId=" + userId)
                .add("name='" + userName + "'")
                .add("email='" + userEmail + "'")
                .toString();
    }
}
