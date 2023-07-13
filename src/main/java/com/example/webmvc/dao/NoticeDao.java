package com.example.webmvc.dao;

import com.example.webmvc.entity.Notice;
import com.example.webmvc.exception.DaoException;


import java.util.Optional;

public interface NoticeDao {
    Optional<Notice> findNoticeByStatus(int id) throws DaoException;

    Optional<Notice> findNoticeByPerson(int id) throws DaoException;

    Optional<Notice> findNoticeByName(String username) throws DaoException;


}
