package com.example.webmvc.dao;

import com.example.webmvc.entity.Notice;
import com.example.webmvc.exception.DaoException;


import java.util.List;
import java.util.Optional;

public interface NoticeDao {

    List<Notice> findNoticeByPerson(String personName) throws DaoException;

    List<Notice> findNoticeByName(String userName) throws DaoException;


}
