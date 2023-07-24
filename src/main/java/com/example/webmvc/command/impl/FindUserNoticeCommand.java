package com.example.webmvc.command.impl;

import com.example.webmvc.command.Command;
import com.example.webmvc.entity.Notice;
import com.example.webmvc.exception.CommandException;
import com.example.webmvc.exception.ServiceException;
import com.example.webmvc.service.NoticeService;
import com.example.webmvc.service.impl.NoticeServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.example.webmvc.command.RequestAttributeName.*;
import static com.example.webmvc.command.RequestAttributeName.MESSAGE;
import static com.example.webmvc.command.SessionAttributeName.USER_ID;

public class FindUserNoticeCommand implements Command {

    private static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute(USER_ID);
        NoticeService noticeService = NoticeServiceImpl.getInstance();
        try {
            List<Notice> noticeList = noticeService.findNoticeByUserId(userId);
            if (!noticeList.isEmpty()) {
                request.setAttribute(NOTICE_LIST, noticeList);
                request.setAttribute(MESSAGE, "notices found: " + noticeList.size());
                logger.info("Notices found: " + noticeList.size());
            } else {
                logger.info("No notices found");
                request.setAttribute(MESSAGE, "No notices found");
            }
        } catch (ServiceException e) {
            logger.error("Failed to find notice by  user id: " + e.getMessage());
            throw new CommandException("Failed to find notice by  user id: ");
        }
        return "/pages/notices.jsp";
    }
}
