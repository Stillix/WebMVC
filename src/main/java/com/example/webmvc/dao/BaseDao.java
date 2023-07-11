package com.example.webmvc.dao;

import com.example.webmvc.entity.AbstractEntity;
import com.example.webmvc.entity.User;
import com.example.webmvc.exception.DaoException;

import java.util.List;
import java.util.Optional;

public abstract class BaseDao<T extends AbstractEntity> {

    public abstract boolean delete(T t) throws DaoException;

    public abstract boolean update(T t) throws DaoException;

    public abstract Optional<T> create(T t) throws DaoException;

    public abstract List<T> findAll() throws DaoException;
}
