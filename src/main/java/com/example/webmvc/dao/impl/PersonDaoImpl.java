package com.example.webmvc.dao.impl;

import com.example.webmvc.dao.BaseDao;
import com.example.webmvc.dao.PersonDao;
import com.example.webmvc.entity.Person;
import com.example.webmvc.exception.DaoException;

import java.util.List;
import java.util.Optional;

public class PersonDaoImpl extends BaseDao<Person> implements PersonDao {
    @Override
    public boolean delete(Person person) throws DaoException {
        return false;
    }

    @Override
    public boolean update(Person person) throws DaoException {
        return false;
    }

    @Override
    public Optional<Person> create(Person person) throws DaoException {
        return Optional.empty();
    }

    @Override
    public List<Person> findAll() throws DaoException {
        return null;
    }

    @Override
    public Optional<Person> findPersonByName(String username) throws DaoException {
        return Optional.empty();
    }

    @Override
    public Optional<Person> findPersonBySurname(String username) throws DaoException {
        return Optional.empty();
    }
}
