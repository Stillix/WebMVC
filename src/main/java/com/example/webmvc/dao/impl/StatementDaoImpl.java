package com.example.webmvc.dao.impl;

import com.example.webmvc.dao.BaseDao;
import com.example.webmvc.dao.StatementDao;
import com.example.webmvc.entity.Statement;
import com.example.webmvc.entity.User;
import com.example.webmvc.exception.DaoException;
import com.example.webmvc.pool.ConnectionPool;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class StatementDaoImpl extends BaseDao<Statement> implements StatementDao {
    private static final String GET_INFO_PERSON = "SELECT * FROM statements WHERE statements.id_person = ?";//fixme
    private static final String GET_BY_STATUS = "SELECT * FROM statements WHERE id_status = ?";

    @Override
    public boolean delete(Statement statement) throws DaoException {
        return false;
    }

    @Override
    public boolean update(Statement statement) throws DaoException {
        return false;
    }

    @Override
    public Optional<Statement> create(Statement statement) throws DaoException {
        return Optional.empty();
    }

    @Override
    public List findAll() throws DaoException {
        return null;
    }

    @Override
    public Optional<Statement> findStatementByStatus(int id) throws DaoException{
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_STATUS)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int idStatement = resultSet.getInt(1);
                int idUser = resultSet.getInt(2);
                int idPerson = resultSet.getInt(3);
                int executionTime = resultSet.getInt(4);
                int reward = resultSet.getInt(5);
                int statusId = resultSet.getInt(6);
                String description = resultSet.getString(7);
                Timestamp publicationDate = resultSet.getTimestamp(8);
                Statement statement2 = new Statement(idStatement, idUser, idPerson, executionTime, publicationDate, reward, statusId, description);
                return Optional.of(statement2);
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public Optional<Statement> findStatementByPerson(int id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_INFO_PERSON)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int idStatement = resultSet.getInt(1);
                int idUser = resultSet.getInt(2);
                int idPerson = resultSet.getInt(3);
                int executionTime = resultSet.getInt(4);
                int reward = resultSet.getInt(5);
                int statusId = resultSet.getInt(6);
                String description = resultSet.getString(7);
                Timestamp publicationDate = resultSet.getTimestamp(8);
                Statement statement1 = new Statement(idStatement, idUser, idPerson, executionTime, publicationDate, reward, statusId, description);
                return Optional.of(statement1);
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public Optional<Statement> findStatementByName(String username) throws DaoException {
        return Optional.empty();
    }
}
