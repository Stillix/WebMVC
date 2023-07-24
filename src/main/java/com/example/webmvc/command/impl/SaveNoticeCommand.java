package com.example.webmvc.command.impl;

import com.example.webmvc.command.Command;
import com.example.webmvc.entity.Notice;
import com.example.webmvc.exception.CommandException;
import com.example.webmvc.exception.ServiceException;
import com.example.webmvc.service.NoticeService;
import com.example.webmvc.service.impl.NoticeServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.util.Optional;

import static com.example.webmvc.command.RequestAttributeName.MESSAGE;
import static com.example.webmvc.command.RequestParameterName.*;
import static com.example.webmvc.command.RequestParameterName.REWARD;
import static com.example.webmvc.command.SessionAttributeName.USER_ID;

public class SaveNoticeCommand implements Command {
    private static Logger logger = LogManager.getLogger();
    private NoticeService noticeService = NoticeServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String title = request.getParameter(TITLE);
        String personName = request.getParameter(NAME);
        String personSurname = request.getParameter(SURNAME);
        int personAge = Integer.parseInt(request.getParameter(AGE));
        int userId = (int) session.getAttribute(USER_ID);
        String personStatus = request.getParameter(PERSON_STATUS);
        String description = request.getParameter(DESCRIPTION);
        int executionTime = Integer.parseInt(request.getParameter(EXECUTION_TIME));
        int reward = Integer.parseInt(request.getParameter(REWARD));
        int noticeId;
        Timestamp publicationDateTime;
        try {
            Optional<Notice> oldNotice = noticeService.findNoticeByNoticeId(Integer.parseInt(request.getParameter(NOTICE_ID)));
            if (oldNotice.isPresent()) {
                publicationDateTime = oldNotice.get().getPublicationDateTime();
                noticeId = oldNotice.get().getNoticeId();
            } else {
                request.setAttribute(MESSAGE, "Notice not found");
                return "/pages/notices.jsp";
            }
            Notice notice = Notice.newBuilder()
                    .setNoticeId(noticeId)
                    .setTitle(title)
                    .setUserId(userId)
                    .setNamePerson(personName)
                    .setSurnamePerson(personSurname)
                    .setAge(personAge)
                    .setPersonStatus(personStatus)
                    .setDescription(description)
                    .setExecutionTime(executionTime)
                    .setReward(reward)
                    .setPublicationDateTime(publicationDateTime)
                    .build();
            boolean updatedNotice = noticeService.updateNotice(notice);
            if (updatedNotice) {
                logger.log(Level.INFO, "Notice was successfully update : ");
                request.setAttribute(MESSAGE, "Notice was successfully update");
                request.setAttribute(NOTICE, notice);
                return "/pages/change_notice_info.jsp";
            } else {
                request.setAttribute(MESSAGE, "Notice not update  ");
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Exception");
            throw new CommandException(e);
        }
        return "/pages/change_notice_info.jsp";
    }
}
