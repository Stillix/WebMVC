package com.example.webmvc.mapper.impl;

import com.example.webmvc.entity.Notice;
import com.example.webmvc.entity.Person;
import com.example.webmvc.mapper.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;

public class PersonMapperImpl implements Mapper<Person> {

    public static final String ID_PERSON = "id_person";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String AGE = "age";
    public static final String DESCRIPTION = "description";
    public static final String PERSON_STATUS = "person_status";

    @Override
    public Person buildEntity(ResultSet resultSet) throws SQLException {
        int idPerson = resultSet.getInt(ID_PERSON);
        String name = resultSet.getString(NAME);
        String surname = resultSet.getString(SURNAME);
        int age = resultSet.getInt(AGE);
        String description = resultSet.getString(DESCRIPTION);
        String personStatus = resultSet.getString(PERSON_STATUS);
        return Person.newBuilder()
                .setPersonId(idPerson)
                .setPersonName(name)
                .setPersonSurname(surname)
                .setPersonAge(age)
                .setDescription(description)
                .setPersonStatus(personStatus)
                .build();
    }
}
