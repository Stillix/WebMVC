package com.example.webmvc.dao.mapper.impl;

import com.example.webmvc.dao.mapper.Mapper;
import com.example.webmvc.entity.Notice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class NoticeMapperImpl implements Mapper<Notice> {

    public static final String ID_NOTICE = "id_notice";
    public static final String TITLE = "title";
    public static final String ID_USER = "id_user";
    public static final String EXECUTION_TIME = "execution_time";
    public static final String REWARD = "reward";
    public static final String ID_STATUS = "id_status";
    public static final String DESCRIPTION = "description";
    public static final String PUBLICATION_DATE = "publication_date";
    public static final String AGE = "age";
    public static final String NAME = "name_person";
    public static final String SURNAME = "surname_person";
    public static final String PERSON_STATUS = "person_status";

    @Override
    public Notice buildEntity(ResultSet resultSet) throws SQLException {
        int idNotice = resultSet.getInt(ID_NOTICE);
        String title = resultSet.getString(TITLE);
        int idUser = resultSet.getInt(ID_USER);
        String name = resultSet.getString(NAME);
        String surname = resultSet.getString(SURNAME);
        int age = resultSet.getInt(AGE);
        String personStatus = resultSet.getString(PERSON_STATUS);
        String description = resultSet.getString(DESCRIPTION);
        int executionTime = resultSet.getInt(EXECUTION_TIME);
        int reward = resultSet.getInt(REWARD);
        int statusId = resultSet.getInt(ID_STATUS);
        Timestamp publicationDate = resultSet.getTimestamp(PUBLICATION_DATE);
        return Notice.newBuilder()
                .setNoticeId(idNotice)
                .setTitle(title)
                .setUserId(idUser)
                .setNamePerson(name)
                .setSurnamePerson(surname)
                .setAge(age)
                .setPersonStatus(personStatus)
                .setDescription(description)
                .setExecutionTime(executionTime)
                .setReward(reward)
                .setStatusId(statusId)
                .setPublicationDateTime(publicationDate).build();
    }
}
