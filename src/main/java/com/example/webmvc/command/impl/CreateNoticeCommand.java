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
        String name = request.getParameter(NAME);
        String surname = request.getParameter(SURNAME);
        int age = Integer.parseInt(request.getParameter(AGE));
        String personStatus = request.getParameter(PERSON_STATUS);
        String description = request.getParameter(DESCRIPTION);
        int executionTime = Integer.parseInt(request.getParameter(EXECUTION_TIME));
        int reward = Integer.parseInt(request.getParameter(REWARD));
        Timestamp publicationDate = new Timestamp(now);
        NoticeService noticeService = NoticeServiceImpl.getInstance();
        Notice notice = Notice.newBuilder()
                .setNoticeId(noticeId)
                .setTitle(title)
                .setUserId(0)
                .setNamePerson(name)
                .setSurnamePerson(surname)
                .setAge(age)
                .setDescription(description)
                .setPersonStatus(personStatus)
                .setExecutionTime(executionTime)
                .setReward(reward)
                .setStatusId(1)
                .setPublicationDateTime(publicationDate)
                .build();
        try {
            Optional<Notice> createdNotice = noticeService.createNotice(notice);
            if (createdNotice.isPresent()) {
                String errorMessage = createdNotice.get().getErrorMessage();
                if (errorMessage == null || errorMessage.isEmpty()) {
                    logger.log(Level.INFO, "Notice was successfully created and added to the database: " + notice);
                    return "/pages/success_notice.jsp";
                } else {
                    request.setAttribute(ERROR_MESSAGE, errorMessage);
                    logger.log(Level.WARN, errorMessage);
                }
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to create notice");
            throw new CommandException(e);
        }
        return "/pages/form_notice.jsp";
    }
}
