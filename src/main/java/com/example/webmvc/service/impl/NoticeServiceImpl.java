package com.example.webmvc.service.impl;

import com.example.webmvc.dao.impl.NoticeDaoImpl;
import com.example.webmvc.entity.Notice;
import com.example.webmvc.exception.DaoException;
import com.example.webmvc.exception.ServiceException;
import com.example.webmvc.service.NoticeService;

import java.util.List;
import java.util.Optional;

public class NoticeServiceImpl implements NoticeService {
    private static NoticeDaoImpl noticeDao = NoticeDaoImpl.getInstance();

    @Override
    public boolean deleteNotice(int noticeId) throws ServiceException {
        boolean match;
        try {
            if (noticeDao.delete(noticeId)) {
                match = true;
            } else {
                match = false;
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return match;
    }


    @Override
    public boolean updateNotice(Notice notice) throws ServiceException {
        boolean match;
        try {
            if (noticeDao.update(notice)) {
                match = true;
            } else {
                match = false;
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return match;
    }

    @Override
    public Optional<Notice> createNotice(Notice notice) throws ServiceException {
        try {
            noticeDao.create(notice);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return Optional.ofNullable(notice);
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
    public Optional<Notice> findNoticeByPerson(String username) throws ServiceException {
        try {
            return noticeDao.findNoticeByPerson(username);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Optional<Notice> findNoticeByName(String username) throws ServiceException {
        try {
            return noticeDao.findNoticeByName(username);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
