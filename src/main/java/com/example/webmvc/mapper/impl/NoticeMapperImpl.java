package com.example.webmvc.mapper.impl;

import com.example.webmvc.entity.Notice;
import com.example.webmvc.mapper.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class NoticeMapperImpl implements Mapper<Notice> {

    public static final String ID_NOTICE = "id_notice";
    public static final String TITLE = "title";
    public static final String ID_USER = "id_user";
    public static final String ID_PERSON = "id_person";
    public static final String EXECUTION_TIME = "execution_time";
    public static final String REWARD = "reward";
    public static final String ID_STATUS = "id_status";
    public static final String DESCRIPTION = "description";
    public static final String PUBLICATION_DATE = "publication_date";

    @Override
    public Notice buildEntity(ResultSet resultSet) throws SQLException {
        int idNotice = resultSet.getInt(ID_NOTICE);
        String title = resultSet.getString(TITLE);
        int idUser = resultSet.getInt(ID_USER);
        int idPerson = resultSet.getInt(ID_PERSON);
        int executionTime = resultSet.getInt(EXECUTION_TIME);
        int reward = resultSet.getInt(REWARD);
        int statusId = resultSet.getInt(ID_STATUS);
        String description = resultSet.getString(DESCRIPTION);
        Timestamp publicationDate = resultSet.getTimestamp(PUBLICATION_DATE);
        return Notice.newBuilder()
                .setNoticeId(idNotice)
                .setTitle(title)
                .setUserId(idUser)
                .setPersonId(idPerson)
                .setExecutionTime(executionTime)
                .setReward(reward)
                .setStatusId(statusId)
                .setDescription(description)
                .setPublicationDateTime(publicationDate).build();
    }
}
