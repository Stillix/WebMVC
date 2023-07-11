package com.example.webmvc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Mapper <T>{
    T buildObj(ResultSet resultSet) throws SQLException;
}
