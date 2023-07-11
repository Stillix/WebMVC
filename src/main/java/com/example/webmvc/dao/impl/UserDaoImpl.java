package com.example.webmvc.dao.impl;

import com.example.webmvc.dao.BaseDao;
import com.example.webmvc.dao.UserDao;
import com.example.webmvc.entity.User;
import com.example.webmvc.exception.DaoException;
import com.example.webmvc.mapper.impl.UserMapperImpl;
import com.example.webmvc.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends BaseDao<User> implements UserDao {
    private static Logger logger = LogManager.getLogger();
    private static final String SELECT_PASSWORD_WHERE_LOGIN = "SELECT password FROM users WHERE login=?";
    private static final String DELETE_USER_WHERE_ID = "DELETE FROM users WHERE id = ?";
    private static final String GET_ALL_USERS = "SELECT * FROM users";
    private static final String GET_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String GET_USER_BY_NAME = "SELECT * FROM users WHERE name = ?";
    private static final String GET_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?";
    private static final String INSERT_USER = "INSERT INTO users (login, password,name,surname,phone,email,id_role) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_USER_WHERE_ID = "UPDATE users SET login = ?, password = ?,name= ?,surname= ?,phone= ?,email= ?,id_role=? WHERE id = ?";

    private static UserDaoImpl instance = new UserDaoImpl();

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean delete(User user) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USER_WHERE_ID)) {
            statement.setInt(1, user.getUserId());
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public boolean update(User user) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_WHERE_ID)) {
            statement.setString(1, user.getUserLogin());
            statement.setString(2, user.getUserPassword());
            statement.setString(3, user.getUserName());
            statement.setString(4, user.getUserSurname());
            statement.setString(5, user.getUserPhone());
            statement.setString(6, user.getUserEmail());
            statement.setInt(7, user.getUserRoleId());
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public Optional<User> create(User user) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getUserLogin());
            statement.setString(2, user.getUserPassword());
            statement.setString(3, user.getUserName());
            statement.setString(4, user.getUserSurname());
            statement.setString(5, user.getUserPhone());
            statement.setString(6, user.getUserEmail());
            statement.setInt(7, user.getUserRoleId());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    int key = resultSet.getInt(1);
                    User createdUser = User.newBuilder()
                            .setUserId(key)
                            .setUserLogin(user.getUserLogin())
                            .setUserPassword(user.getUserPassword())
                            .setUserName(user.getUserName())
                            .setUserSurname(user.getUserSurname())
                            .setUserPhone(user.getUserPhone())
                            .setUserEmail(user.getUserEmail())
                            .setUserRoleId(user.getUserRoleId())
                            .build();
                    return Optional.of(createdUser);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to create user", e);
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> userList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL_USERS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int userId = resultSet.getInt(1);
                String userLogin = resultSet.getString(2);
                String userPassword = resultSet.getString(3);
                String userName = resultSet.getString(4);
                String userSurname = resultSet.getString(5);
                String userPhone = resultSet.getString(6);
                String userEmail = resultSet.getString(7);
                int userRoleId = resultSet.getInt(8);
                User createdUser = User.newBuilder()
                        .setUserId(userId)
                        .setUserLogin(userLogin)
                        .setUserPassword(userPassword)
                        .setUserName(userName)
                        .setUserSurname(userSurname)
                        .setUserPhone(userPhone)
                        .setUserEmail(userEmail)
                        .setUserRoleId(userRoleId)
                        .build();
                userList.add(createdUser);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return userList;
    }

    @Override
    public Optional<User> getUserById(int id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_USER_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int userId = resultSet.getInt(1);
                    String userLogin = resultSet.getString(2);
                    String userPassword = resultSet.getString(3);
                    String userName = resultSet.getString(4);
                    String userSurname = resultSet.getString(5);
                    String userPhone = resultSet.getString(6);
                    String userEmail = resultSet.getString(7);
                    int userRoleId = resultSet.getInt(8);
                    User createdUser = User.newBuilder()
                            .setUserId(userId)
                            .setUserLogin(userLogin)
                            .setUserPassword(userPassword)
                            .setUserName(userName)
                            .setUserSurname(userSurname)
                            .setUserPhone(userPhone)
                            .setUserEmail(userEmail)
                            .setUserRoleId(userRoleId)
                            .build();
                    return Optional.of(createdUser);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getUserByName(String username) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_USER_BY_NAME)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int userId = resultSet.getInt(1);
                    String userLogin = resultSet.getString(2);
                    String userPassword = resultSet.getString(3);
                    String userName = resultSet.getString(4);
                    String userSurname = resultSet.getString(5);
                    String userPhone = resultSet.getString(6);
                    String userEmail = resultSet.getString(7);
                    int userRoleId = resultSet.getInt(8);
                    User createdUser = User.newBuilder()
                            .setUserId(userId)
                            .setUserLogin(userLogin)
                            .setUserPassword(userPassword)
                            .setUserName(userName)
                            .setUserSurname(userSurname)
                            .setUserPhone(userPhone)
                            .setUserEmail(userEmail)
                            .setUserRoleId(userRoleId)
                            .build();
                    return Optional.of(createdUser);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getUserByLogin(String login) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_USER_BY_LOGIN)) {
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    UserMapperImpl userMapper = new UserMapperImpl();
                    return Optional.ofNullable(userMapper.buildObj(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.empty();
    }

    @Override
    public boolean authenticate(String login, String password) throws DaoException {
        boolean match = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_PASSWORD_WHERE_LOGIN)) {
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                String passFromDb;
                if (resultSet.next()) {
                    passFromDb = resultSet.getString(1);
                    match = password.equals(passFromDb);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return match;
    }
}
