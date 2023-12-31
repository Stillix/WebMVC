package com.example.webmvc.dao.impl;

import com.example.webmvc.dao.BaseDao;
import com.example.webmvc.dao.NoticeDao;
import com.example.webmvc.entity.Notice;
import com.example.webmvc.exception.DaoException;
import com.example.webmvc.dao.mapper.impl.NoticeMapperImpl;
import com.example.webmvc.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NoticeDaoImpl extends BaseDao<Notice> implements NoticeDao {
    private static final String SELECT_NOTICE_BY_SURNAME_PERSON = "SELECT id_notice,title, id_user, name_person,surname_person,age,person_status,description,execution_time, reward, id_status, publication_date FROM notices WHERE surname = ?";
    private static final String SELECT_NOTICE_BY_USER_ID = "SELECT id_notice,title, name_person,surname_person,age,person_status,description,execution_time, reward, publication_date FROM notices WHERE id_user = ?";
    private static final String SELECT_NOTICE_BY_NOTICE_ID = "SELECT id_notice,title,id_user, name_person,surname_person,age,person_status,description,execution_time, reward, publication_date FROM notices WHERE id_notice = ?";
    private static final String SELECT_NOTICE_BY_USERNAME = "SELECT notices.id_notice,notices.title, notices.id_user, notices.name_person,notices.surname_person,notices.age,notices.description, notices.execution_time, notices.reward, notices.id_status,  notices.publication_date FROM notices WHERE notices.id_user IN (SELECT id_user FROM users WHERE surname = ?)";
    private static final String SELECT_ALL_NOTICES = "SELECT  id_notice,title,id_user, name_person,surname_person,age,person_status,description,execution_time, reward, id_status, publication_date FROM notices";
    private static final String INSERT_NOTICE = "INSERT INTO notices (title, name_person,surname_person,age,person_status,description, execution_time, reward, id_user, id_status,publication_date) VALUES (?, ?, ?, ?, ?, ?, ?,?,?,?,?)";
    private static final String UPDATE_NOTICE_WHERE_ID = "UPDATE notices SET title = ?,name_person=?,surname_person= ?,age= ?,person_status= ?, description= ?,execution_time= ?,reward= ? WHERE id_notice = ?";
    private static final String DELETE_NOTICE_WHERE_ID = "DELETE FROM notices WHERE id_notice = ?";
    private static NoticeDaoImpl instance = new NoticeDaoImpl();

    private NoticeDaoImpl() {
    }

    public static NoticeDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean delete(int noticeId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_NOTICE_WHERE_ID)) {
            statement.setInt(1, noticeId);
            int rowsDeleted = statement.executeUpdate();
            return (rowsDeleted > 0);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public boolean update(Notice notice) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_NOTICE_WHERE_ID)) {
            setStatementParameters(statement, notice);
            statement.setInt(9, notice.getNoticeId());
            int rowsUpdated = statement.executeUpdate();
            return (rowsUpdated > 0);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public Optional<Notice> create(Notice notice) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_NOTICE, Statement.RETURN_GENERATED_KEYS)) {
            setStatementParameters(statement, notice);
            statement.setInt(9, notice.getUserId());
            statement.setInt(10, notice.getStatusId());
            statement.setTimestamp(11, notice.getPublicationDateTime());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    int key = resultSet.getInt(1);
                    Notice createdNotice = Notice.newBuilder()
                            .setNoticeId(key)
                            .build();
                    return Optional.of(createdNotice);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to create notice", e);
        }
        return Optional.empty();
    }

    @Override
    public List findAll() throws DaoException {
        List<Notice> userList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_NOTICES);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                NoticeMapperImpl noticeMapper = new NoticeMapperImpl();
                Notice notice = noticeMapper.buildEntity(resultSet);
                userList.add(notice);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return userList;
    }

    @Override
    public List<Notice> findNoticeByPerson(String surname) throws DaoException {
        List<Notice> noticeList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_NOTICE_BY_SURNAME_PERSON)) {
            statement.setString(1, surname);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    NoticeMapperImpl noticeMapper = new NoticeMapperImpl();
                    Notice notice = noticeMapper.buildEntity(resultSet);
                    noticeList.add(notice);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return noticeList;
    }

    @Override
    public List<Notice> findNoticeByName(String username) throws DaoException {
        List<Notice> noticeList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_NOTICE_BY_USERNAME)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    NoticeMapperImpl noticeMapper = new NoticeMapperImpl();
                    Notice notice = noticeMapper.buildEntity(resultSet);
                    noticeList.add(notice);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return noticeList;
    }

    @Override
    public List<Notice> findNoticeByUserId(int id) throws DaoException {
        List<Notice> noticeList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_NOTICE_BY_USER_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    NoticeMapperImpl noticeMapper = new NoticeMapperImpl();
                    Notice notice = noticeMapper.buildEntity(resultSet);
                    noticeList.add(notice);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return noticeList;
    }

    @Override
    public Optional<Notice> findNoticeByNoticeId(int id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_NOTICE_BY_NOTICE_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                NoticeMapperImpl noticeMapper = new NoticeMapperImpl();
                Notice notice = noticeMapper.buildEntity(resultSet);
                return Optional.of(notice);
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        return Optional.empty();
    }


    private void setStatementParameters(PreparedStatement statement, Notice notice) throws SQLException {
        statement.setString(1, notice.getTitle());
        statement.setString(2, notice.getPersonName());
        statement.setString(3, notice.getPersonSurname());
        statement.setInt(4, notice.getPersonAge());
        statement.setString(5, notice.getPersonStatus());
        statement.setString(6, notice.getDescription());
        statement.setInt(7, notice.getExecutionTime());
        statement.setInt(8, notice.getReward());
    }
}
