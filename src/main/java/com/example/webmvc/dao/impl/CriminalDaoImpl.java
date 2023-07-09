package com.example.webmvc.dao.impl;

import com.example.webmvc.dao.BaseDao;
import com.example.webmvc.dao.CriminalDao;
import com.example.webmvc.entity.Criminal;
import com.example.webmvc.entity.User;
import com.example.webmvc.exception.DaoException;
import com.example.webmvc.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CriminalDaoImpl extends BaseDao<Criminal> implements CriminalDao {
    private static Logger logger = LogManager.getLogger();
    private static final String DELETE_CRIMINAL_WHERE_ID = "DELETE FROM criminals WHERE id = ?";

    @Override
    public boolean delete(Criminal criminal) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_CRIMINAL_WHERE_ID)) {
            statement.setInt(1, criminal.getCriminalId());
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public boolean update(Criminal criminal) throws DaoException {
        return false;
    }

    @Override
    public Optional<User> create(User user) throws DaoException {
        return Optional.empty();
    }

    @Override
    public List<Criminal> findAll() throws DaoException {
        return null;
    }

    @Override
    public Criminal getCriminalById(int id) {
        return null;
    }

    @Override
    public Criminal getCriminalByName(String name) {
        return null;
    }


}
