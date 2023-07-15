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
    private static final String SELECT_USER_BY_LOGIN = "SELECT id_user, login, password, name, surname, phone, email, role FROM users WHERE login = ?";
    private static final String SELECT_ALL_USERS = "SELECT id_user, login, password, name, surname, phone, email, role FROM users";
    private static final String SELECT_USER_BY_NAME = "SELECT id_user, login, password, name, surname, phone, email, role FROM users WHERE name = ?";
    private static final String INSERT_USER = "INSERT INTO users (name,surname,phone,email,role,login, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_USER_WHERE_ID = "UPDATE users SET name= ?,surname= ?,phone= ?,email= ?,role=? WHERE id_user = ?";
    private static final String DELETE_USER_WHERE_ID = "DELETE FROM users WHERE id_user = ?";

    private static UserDaoImpl instance = new UserDaoImpl();

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean delete(int userId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USER_WHERE_ID)) {
            statement.setInt(1, userId);
            int rowsDeleted = statement.executeUpdate();
            return (rowsDeleted > 0);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public boolean update(User user) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_WHERE_ID)) {
            setStatementParameters(statement, user);
            statement.setInt(6, user.getUserId());
            int rowsUpdated = statement.executeUpdate();
            return (rowsUpdated > 0);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public Optional<User> create(User user) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {
            setStatementParameters(statement, user);
            statement.setString(6, user.getUserLogin());
            statement.setString(7, user.getUserPassword());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    int key = resultSet.getInt(1);
                    user = User.newBuilder()
                            .setUserId(key)
                            .build();
                    return Optional.of(user);
                }
            }
        } catch (SQLException e) {
            logger.error("Failed to create user");
            throw new DaoException("Failed to create user" + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> userList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USERS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                UserMapperImpl userMapper = new UserMapperImpl();
                User createdUser = userMapper.buildEntity(resultSet);
                userList.add(createdUser);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return userList;
    }

    @Override
    public Optional<User> findUserByName(String username) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_NAME)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    UserMapperImpl userMapper = new UserMapperImpl();
                    return Optional.of(userMapper.buildEntity(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findUserByLogin(String login) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_LOGIN)) {
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    UserMapperImpl userMapper = new UserMapperImpl();
                    return Optional.of(userMapper.buildEntity(resultSet));
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

    private void setStatementParameters(PreparedStatement statement, User user) throws SQLException {
        statement.setString(1, user.getUserName());
        statement.setString(2, user.getUserSurname());
        statement.setString(3, user.getUserPhone());
        statement.setString(4, user.getUserEmail());
        statement.setString(5, user.getUserRole());
    }
}
