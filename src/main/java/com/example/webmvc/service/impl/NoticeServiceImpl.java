package com.example.webmvc.service.impl;

import com.example.webmvc.dao.impl.NoticeDaoImpl;

import com.example.webmvc.entity.Notice;
import com.example.webmvc.entity.User;
import com.example.webmvc.exception.DaoException;
import com.example.webmvc.exception.ServiceException;
import com.example.webmvc.service.NoticeService;
import com.example.webmvc.validator.NoticeValidator;
import com.example.webmvc.validator.impl.NoticeValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class NoticeServiceImpl implements NoticeService {
    private static Logger logger = LogManager.getLogger();
    private static NoticeServiceImpl instance = new NoticeServiceImpl();
    private NoticeDaoImpl noticeDao = NoticeDaoImpl.getInstance();
    private static NoticeValidatorImpl noticeValidator = new NoticeValidatorImpl();

    private NoticeServiceImpl() {
    }

    public static NoticeServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean deleteNotice(int noticeId) throws ServiceException {
        try {
            return noticeDao.delete(noticeId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }


    @Override
    public boolean updateNotice(Notice notice) throws ServiceException {
        try {
            return noticeDao.update(notice);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public Optional<Notice> createNotice(Notice notice) throws ServiceException {
        Map<String, String> validNotice = noticeValidator.isValidNotice(notice);
        if (validNotice.isEmpty()) {
            try {
                noticeDao.create(notice);
            } catch (DaoException e) {
                throw new ServiceException("Failed to create notice" + e);
            }
            return Optional.of(notice);
        } else {
            return getNotice(validNotice);
        }
    }

    private static Optional<Notice> getNotice(Map<String, String> errorMessage) {
        Notice errorNotice = Notice.newBuilder().setErrorMessage(errorMessage).build();
        return Optional.of(errorNotice);
    }

    @Override
    public List findAllNotice() throws ServiceException {
        try {
            return noticeDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Notice> findNoticeByPerson(String surname) throws ServiceException {
        try {
            List<Notice> noticeList = noticeDao.findNoticeByPerson(surname);
            return noticeList;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Notice> findNoticeByName(String username) throws ServiceException {
        try {
            List<Notice> noticeList = noticeDao.findNoticeByPerson(username);
            return noticeList;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
