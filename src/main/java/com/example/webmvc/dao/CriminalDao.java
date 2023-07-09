package com.example.webmvc.dao;

import com.example.webmvc.entity.Criminal;

import java.util.List;

public interface CriminalDao {

    Criminal getCriminalById(int id);
    Criminal getCriminalByName(String name);

}
