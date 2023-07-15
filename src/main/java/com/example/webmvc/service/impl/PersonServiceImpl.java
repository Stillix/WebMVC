package com.example.webmvc.service.impl;


import com.example.webmvc.dao.PersonDao;
import com.example.webmvc.dao.impl.NoticeDaoImpl;
import com.example.webmvc.dao.impl.PersonDaoImpl;
import com.example.webmvc.entity.Person;
import com.example.webmvc.entity.User;
import com.example.webmvc.exception.DaoException;
import com.example.webmvc.exception.ServiceException;
import com.example.webmvc.service.PersonService;

import java.util.List;
import java.util.Optional;


public class PersonServiceImpl implements PersonService {
    private static PersonDaoImpl personDao = PersonDaoImpl.getInstance();
    @Override
    public Optional<Person> findPersonBySurname(String surname) throws ServiceException {
        try {
            return personDao.findPersonBySurname(surname);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Person> findPersonByStatus(String status) throws ServiceException {
        try {
            return personDao.findPersonByStatus(status);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Person> findPersonByDescription(String description) throws ServiceException {
        try {
            return personDao.findPersonByDescription(description);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deletePerson(int personId) throws ServiceException {
        boolean match;
        try {
            if (personDao.delete(personId)) {
                match = true;
            } else {
                match = false;
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return match;
    }
    @Override
    public List findAllPersons() throws ServiceException {
        try {
            return personDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public boolean updatePerson(Person person) throws ServiceException {
        boolean match;
        try {
            if (personDao.update(person)) {
                match = true;
            } else {
                match = false;
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return match;
    }
}
