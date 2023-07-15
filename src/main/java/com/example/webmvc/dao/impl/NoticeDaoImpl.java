package com.example.webmvc.dao.impl;

import com.example.webmvc.dao.BaseDao;
import com.example.webmvc.dao.NoticeDao;
import com.example.webmvc.entity.Notice;
import com.example.webmvc.exception.DaoException;
import com.example.webmvc.mapper.impl.NoticeMapperImpl;
import com.example.webmvc.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NoticeDaoImpl extends BaseDao<Notice> implements NoticeDao {
    private static final String SELECT_NOTICE_BY_SURNAME_PERSON = "SELECT notices.id_notice,notices.title, notices.id_user, notices.id_person, notices.execution_time, notices.reward, notices.id_status, notices.description, notices.publication_date FROM notices WHERE notices.id_person IN (SELECT id_person FROM persons WHERE surname = ?);";
    private static final String SELECT_NOTICE_BY_USERNAME = "SELECT notices.id_notice,notices.title, notices.id_user, notices.id_person, notices.execution_time, notices.reward, notices.id_status, notices.description, notices.publication_date FROM notices WHERE notices.id_user IN (SELECT id_user FROM users WHERE surname = ?)";
    private static final String SELECT_ALL_NOTICES = "SELECT id_notice,title, id_user, id_person, execution_time, reward, id_status, description, publication_date FROM notices";
    private static final String INSERT_NOTICE = "INSERT INTO notices (title, id_user, id_person, execution_time, reward, id_status, description, publication_date) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
    private static final String UPDATE_NOTICE_WHERE_ID = "UPDATE notice SET title = ?, id_user = ?,id_person= ?,execution_time= ?,reward= ?,id_status= ?,description=?,publication_date=? WHERE id_notice = ?";
    private static final String DELETE_NOTICE_WHERE_ID = "DELETE FROM notice WHERE id_notice = ?";
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
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    int key = resultSet.getInt(1);
                    Notice createdNotice = Notice.newBuilder()
                            .setNoticeId(key)
                            .setTitle(notice.getTitle())
                            .setUserId(notice.getUserId())
                            .setPersonId(notice.getPersonId())
                            .setExecutionTime(notice.getExecutionTime())
                            .setReward(notice.getReward())
                            .setStatusId(notice.getStatusId())
                            .setDescription(notice.getDescription())
                            .setPublicationDateTime(notice.getPublicationDateTime())
                            .build();
                    return Optional.of(createdNotice);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to create user", e);
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
    public Optional<Notice> findNoticeByPerson(String surname) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_NOTICE_BY_SURNAME_PERSON)) {
            statement.setString(1, surname);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                NoticeMapperImpl noticeMapper = new NoticeMapperImpl();
                return Optional.ofNullable(noticeMapper.buildEntity(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Notice> findNoticeByName(String username) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_NOTICE_BY_USERNAME)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                NoticeMapperImpl noticeMapper = new NoticeMapperImpl();
                return Optional.ofNullable(noticeMapper.buildEntity(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        return Optional.empty();
    }

    private void setStatementParameters(PreparedStatement statement, Notice notice) throws SQLException {
        statement.setString(1, notice.getTitle());
        statement.setInt(2, notice.getUserId());
        statement.setInt(3, notice.getPersonId());
        statement.setInt(4, notice.getExecutionTime());
        statement.setInt(5, notice.getReward());
        statement.setInt(6, notice.getStatusId());
        statement.setString(7, notice.getDescription());
        statement.setTimestamp(8, notice.getPublicationDateTime());
    }
}
