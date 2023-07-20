package com.example.webmvc.command.impl;

import com.example.webmvc.command.Command;
import com.example.webmvc.entity.Notice;
import com.example.webmvc.entity.User;
import com.example.webmvc.exception.CommandException;
import com.example.webmvc.exception.ServiceException;
import com.example.webmvc.service.NoticeService;
import com.example.webmvc.service.UserService;
import com.example.webmvc.service.impl.NoticeServiceImpl;
import com.example.webmvc.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.util.Map;
import java.util.Optional;

import static com.example.webmvc.command.RequestAttributeName.ERROR_MESSAGE;
import static com.example.webmvc.command.RequestParameterName.*;


public class CreateNoticeCommand implements Command {


    private static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        int noticeId = 0;
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
        NoticeService noticeService = NoticeServiceImpl.getInstance();
        Notice notice = Notice.newBuilder()
                .setNoticeId(noticeId)
                .setTitle(title)
                .setUserId(50)
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
                    logger.log(Level.INFO, "User was successfully created and added to the database: " + createdNotice);
                    return "/pages/success_notice.jsp";
                } else {
                    for (Map.Entry<String, String> error : errorMessage.entrySet()) {
                        request.setAttribute(error.getKey(), error.getValue());
                    }
                }
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Except");
            throw new CommandException(e);
        }
        return "/pages/form_notice.jsp";
    }
}
