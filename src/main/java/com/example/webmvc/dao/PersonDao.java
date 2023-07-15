package com.example.webmvc.dao;

import com.example.webmvc.entity.Person;
import com.example.webmvc.exception.DaoException;

import java.util.Optional;

public interface PersonDao {
    Optional<Person> findPersonBySurname(String surname) throws DaoException;

    Optional<Person> findPersonByStatus(String status) throws DaoException;

    Optional<Person> findPersonByDescription(String description) throws DaoException;

}
