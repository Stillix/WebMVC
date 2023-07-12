package com.example.webmvc.dao;

import com.example.webmvc.entity.Statement;
import com.example.webmvc.exception.DaoException;


import java.util.Optional;

public interface StatementDao {
    Optional<Statement> findStatementByStatus(int id) throws DaoException;

    Optional<Statement> findStatementByPerson(int id) throws DaoException;

    Optional<Statement> findStatementByName(String username) throws DaoException;


}
