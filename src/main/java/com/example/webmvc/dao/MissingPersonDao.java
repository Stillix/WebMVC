package com.example.webmvc.dao;

import com.example.webmvc.entity.MissingPerson;

import java.util.List;

public interface MissingPersonDao {

    MissingPerson getMissingPersonById(int id);
    MissingPerson getMissingPersonByName(String name);

}
