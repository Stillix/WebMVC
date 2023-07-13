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
    private String userRole;

    private User() {
    }

    public int getUserId() {
        return userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserRole() {
        return userRole;
    }

    public static Builder newBuilder() {
        return new User().new Builder();
    }


    public class Builder {

        private Builder() {
        }

        public Builder setUserId(int userId) {
            User.this.userId = userId;
            return this;
        }

        public Builder setUserLogin(String userLogin) {
            User.this.userLogin = userLogin;
            return this;
        }

        public Builder setUserPassword(String userPassword) {
            User.this.userPassword = userPassword;
            return this;
        }

        public Builder setUserName(String userName) {
            User.this.userName = userName;
            return this;
        }

        public Builder setUserSurname(String userSurname) {
            User.this.userSurname = userSurname;
            return this;
        }

        public Builder setUserPhone(String userPhone) {
            User.this.userPhone = userPhone;
            return this;
        }

        public Builder setUserEmail(String userEmail) {
            User.this.userEmail = userEmail;
            return this;
        }

        public Builder setUserRole(String userRole) {
            User.this.userRole = userRole;
            return this;
        }
        public User build() {
            return User.this;
        }
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
                .add("userLogin='" + userLogin + "'")
                .add("userPassword='" + userPassword + "'")
                .add("userName='" + userName + "'")
                .add("userSurname='" + userSurname + "'")
                .add("userPhone='" + userPhone + "'")
                .add("userEmail='" + userEmail + "'")
                .add("userRole=" + userRole)
                .toString();
    }
}
