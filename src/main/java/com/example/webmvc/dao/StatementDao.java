package com.example.webmvc.dao;

import com.example.webmvc.entity.Statement;
import com.example.webmvc.entity.User;
import com.example.webmvc.exception.DaoException;


import java.util.Optional;

public interface StatementDao {
    Optional<Statement> getStatementByStatus(int id) throws DaoException;

    Optional<Statement> getStatementByPerson(int id) throws DaoException;

    Optional<Statement> getStatementByName(String username) throws DaoException;


}
