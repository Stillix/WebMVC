package com.example.webmvc.entity;

import java.util.Objects;
import java.util.StringJoiner;

public class User extends AbstractEntity {
    private int userId;
    private String userLogin;
    private String userPassword;
    private String userName;
    private String userSurname;
    private String userPhone;
    private String userEmail;
    private int userRoleId;

    public User(int userId, String userLogin, String userPassword, String userName, String userSurname, String userPhone, String userEmail, int userRoleId) {
        this.userLogin = userLogin;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.userRoleId = userRoleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userLogin, user.userLogin) && Objects.equals(userPassword, user.userPassword) && Objects.equals(userName, user.userName) && Objects.equals(userSurname, user.userSurname) && Objects.equals(userPhone, user.userPhone) && Objects.equals(userEmail, user.userEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userLogin, userPassword, userName, userSurname, userPhone, userEmail);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("userId=" + userId)
                .add("userName='" + userName + "'")
                .add("userSurname='" + userSurname + "'")
                .add("userPhone='" + userPhone + "'")
                .add("userEmail='" + userEmail + "'")
                .toString();
    }
}
