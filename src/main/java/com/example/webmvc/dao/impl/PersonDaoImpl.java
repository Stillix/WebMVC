package com.example.webmvc.dao.impl;

import com.example.webmvc.dao.BaseDao;
import com.example.webmvc.dao.PersonDao;
import com.example.webmvc.entity.Person;
import com.example.webmvc.exception.DaoException;
import com.example.webmvc.dao.mapper.impl.PersonMapperImpl;
import com.example.webmvc.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonDaoImpl extends BaseDao<Person> implements PersonDao {
    private static Logger logger = LogManager.getLogger();
    private static final String SELECT_ALL_PERSONS = "SELECT id_person, name, surname, age, description, person_status FROM persons";
    private static final String SELECT_PERSON_BY_SURNAME = "SELECT  id_person, name, surname, age, description, person_status FROM persons WHERE surname = ?";
    private static final String SELECT_PERSON_BY_DESCRIPTION = "SELECT  id_person, name, surname, age, description, person_status FROM persons WHERE description LIKE  ?";
    private static final String SELECT_PERSON_BY_STATUS = "SELECT  id_person, name, surname, age, description, person_status FROM persons WHERE person_status = ?";
    private static final String INSERT_PERSON = "INSERT INTO persons (name, surname,age,description,person_status) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_PERSON_WHERE_ID = "UPDATE person SET name = ?, surname = ?,age= ?,description= ?,person_status= ? WHERE id_person = ?";
    private static final String DELETE_PERSON_WHERE_ID = "DELETE FROM persons WHERE id_person = ?";
    private static PersonDaoImpl instance = new PersonDaoImpl();

    private PersonDaoImpl() {
    }

    public static PersonDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean delete(int id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PERSON_WHERE_ID)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            return (rowsDeleted > 0);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public boolean update(Person person) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PERSON_WHERE_ID)) {
            setStatementParameters(statement, person);
            statement.setInt(6,person.getPersonId());
            int rowsUpdated = statement.executeUpdate();
            return (rowsUpdated > 0);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public Optional<Person> create(Person person) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_PERSON, Statement.RETURN_GENERATED_KEYS)) {
            setStatementParameters(statement, person);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    int key = resultSet.getInt(1);
                    person = Person.newBuilder()
                            .setPersonId(key)
                            .build();
                    return Optional.of(person);
                }
            }
        } catch (SQLException e) {
            logger.error("Failed to create user");
            throw new DaoException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Person> findAll() throws DaoException {
        List<Person> personList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_PERSONS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                PersonMapperImpl personMapper = new PersonMapperImpl();
                Person createdPerson = personMapper.buildEntity(resultSet);
                personList.add(createdPerson);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return personList;
    }

    @Override
    public Optional<Person> findPersonBySurname(String surname) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_PERSON_BY_SURNAME)) {
            statement.setString(1, surname);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    PersonMapperImpl personMapper = new PersonMapperImpl();
                    return Optional.of(personMapper.buildEntity(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Person> findPersonByStatus(String status) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_PERSON_BY_STATUS)) {
            statement.setString(1, status);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                PersonMapperImpl personMapper = new PersonMapperImpl();
                Person person = personMapper.buildEntity(resultSet);
                return Optional.of(person);
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Person> findPersonByDescription(String description) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_PERSON_BY_DESCRIPTION)) {
            statement.setString(1, description);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                PersonMapperImpl personMapper = new PersonMapperImpl();
                Person person = personMapper.buildEntity(resultSet);
                return Optional.of(person);
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        return Optional.empty();
    }

    private void setStatementParameters(PreparedStatement statement, Person person) throws SQLException {
        statement.setString(1, person.getPersonName());
        statement.setString(2, person.getPersonSurname());
        statement.setInt(3, person.getPersonAge());
        statement.setString(4, person.getDescription());
        statement.setString(5, person.getPersonStatus());
    }

}
