package com.example.webmvc.dao;

import com.example.webmvc.entity.Person;
import com.example.webmvc.exception.DaoException;

import java.util.Optional;

public interface PersonDao {
    Optional<Person> findPersonByName(String username) throws DaoException;

    Optional<Person> findPersonBySurname(String username) throws DaoException;

}
