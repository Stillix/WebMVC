package com.example.webmvc.dao;

import com.example.webmvc.entity.AbstractEntity;
import com.example.webmvc.exception.DaoException;

import java.util.List;

public abstract class BaseDao<T extends AbstractEntity> {

    public abstract boolean delete(T t) throws DaoException;

    public abstract T update(T t) throws DaoException;

    public abstract T create() throws DaoException;

    public abstract List<T> findAll() throws DaoException;
}
