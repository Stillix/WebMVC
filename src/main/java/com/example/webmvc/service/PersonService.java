package com.example.webmvc.service;

import com.example.webmvc.dao.PersonDao;
import com.example.webmvc.entity.Person;
import com.example.webmvc.entity.User;
import com.example.webmvc.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    Optional<Person> findPersonBySurname(String surname) throws ServiceException;

    Optional<Person> findPersonByStatus(String status) throws ServiceException;

    Optional<Person> findPersonByDescription(String description) throws ServiceException;

    boolean deletePerson(int personId) throws ServiceException;

    List findAllPersons() throws ServiceException;

    boolean updatePerson(Person person) throws ServiceException;
}
