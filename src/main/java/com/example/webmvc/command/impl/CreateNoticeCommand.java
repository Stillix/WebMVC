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
import java.util.Map;
import java.util.Optional;

import static com.example.webmvc.command.RequestParameterName.*;
import static com.example.webmvc.command.SessionAttributeName.*;
import static com.example.webmvc.command.SessionAttributeName.NOTICE;
import static com.example.webmvc.command.SessionAttributeName.USER_ID;


public class CreateNoticeCommand implements Command {
    private static Logger logger = LogManager.getLogger();
    private NoticeService noticeService = NoticeServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        int noticeId = 0;
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute(USER_ID);
        long now = System.currentTimeMillis();
        String title = request.getParameter(TITLE);
        String personName = request.getParameter(NAME);
        String personSurname = request.getParameter(SURNAME);
        int personAge = Integer.parseInt(request.getParameter(AGE));
        String personStatus = request.getParameter(PERSON_STATUS);
        String description = request.getParameter(DESCRIPTION);
        int executionTime = Integer.parseInt(request.getParameter(EXECUTION_TIME));
        int reward = Integer.parseInt(request.getParameter(REWARD));
        Timestamp publicationDateTime = new Timestamp(now);
        Notice notice = Notice.newBuilder()
                .setNoticeId(noticeId)
                .setTitle(title)
                .setUserId(userId)
                .setNamePerson(personName)
                .setSurnamePerson(personSurname)
                .setAge(personAge)
                .setDescription(description)
                .setPersonStatus(personStatus)
                .setExecutionTime(executionTime)
                .setReward(reward)
                .setStatusId(1)
                .setPublicationDateTime(publicationDateTime)
                .build();
        try {
            Optional<Notice> createdNotice = noticeService.createNotice(notice);
            if (createdNotice.isPresent()) {
                Map<String, String> errorMessage = createdNotice.get().getErrorMessages();
                if (errorMessage == null || errorMessage.isEmpty()) {
                    session.setAttribute(NOTICE, notice);
                    logger.log(Level.INFO, "Notice was successfully created and added to the database: " + createdNotice);
                    return "/pages/success_notice.jsp";
                } else {
                    for (Map.Entry<String, String> error : errorMessage.entrySet()) {
                        request.setAttribute(error.getKey(), error.getValue());
                    }
                }
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Exception");
            throw new CommandException(e);
        }
        return "/pages/form_notice.jsp";
    }
}
