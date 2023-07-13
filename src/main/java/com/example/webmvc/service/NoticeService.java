package com.example.webmvc.service;

import com.example.webmvc.entity.Notice;
import com.example.webmvc.exception.DaoException;
import com.example.webmvc.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface NoticeService {
    boolean deleteNotice(Notice notice) throws ServiceException;

    boolean updateNotice(Notice notice) throws ServiceException;

    Optional<Notice> createNotice(Notice notice) throws ServiceException;

    List findAllNotice() throws ServiceException;

    Optional<Notice> findNoticeByStatus(int id) throws ServiceException;

    Optional<Notice> findNoticeByPerson(int id) throws ServiceException;

    Optional<Notice> findNoticeByName(String username) throws ServiceException;

}
