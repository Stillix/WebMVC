package com.example.webmvc.dao;

import com.example.webmvc.entity.Person;
import com.example.webmvc.exception.DaoException;

import java.util.Optional;

public interface PersonDao {
    Optional<Person> getPersonByName(String username) throws DaoException;

    Optional<Person> getPersonBySurname(String username) throws DaoException;

}
