package com.example.webmvc.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Mapper <T>{
    T buildEntity(ResultSet resultSet) throws SQLException;
}
