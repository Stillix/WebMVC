package com.example.webmvc.dao;

import com.example.webmvc.entity.Notice;
import com.example.webmvc.exception.DaoException;


import java.util.Optional;

public interface NoticeDao {

    Optional<Notice> findNoticeByPerson(String personName) throws DaoException;

    Optional<Notice> findNoticeByName(String userName) throws DaoException;


}
