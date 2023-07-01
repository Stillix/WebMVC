package com.example.webmvc.dao;

import com.example.webmvc.entity.AbstractEntity;

import java.util.List;

public abstract class BaseDao<T extends AbstractEntity> {

    public abstract boolean insert(T t);

    public abstract boolean delete(T t);

    public abstract T update(T t);

    public abstract T create();

    public abstract List<T> findAll();
}
