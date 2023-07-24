package com.example.webmvc.service;

import com.example.webmvc.entity.Notice;
import com.example.webmvc.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface NoticeService {

    boolean deleteNotice(int noticeId) throws ServiceException;

    boolean updateNotice(Notice notice) throws ServiceException;

    Optional<Notice> createNotice(Notice notice) throws ServiceException;

    List findAllNotice() throws ServiceException;

    List<Notice> findNoticeByPerson(String surname) throws ServiceException;

    List<Notice> findNoticeByName(String username) throws ServiceException;
    List<Notice> findNoticeByUserId(int id) throws ServiceException;
    Optional<Notice> findNoticeByNoticeId(int id) throws ServiceException;

}
