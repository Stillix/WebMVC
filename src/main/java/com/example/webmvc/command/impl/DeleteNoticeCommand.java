package com.example.webmvc.command.impl;

import com.example.webmvc.command.Command;
import com.example.webmvc.entity.Notice;
import com.example.webmvc.exception.CommandException;
import com.example.webmvc.exception.ServiceException;
import com.example.webmvc.service.NoticeService;
import com.example.webmvc.service.UserService;
import com.example.webmvc.service.impl.NoticeServiceImpl;
import com.example.webmvc.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.example.webmvc.command.RequestAttributeName.MESSAGE;
import static com.example.webmvc.command.RequestAttributeName.NOTICE_LIST;
import static com.example.webmvc.command.RequestParameterName.USER_ID;
import static com.example.webmvc.command.SessionAttributeName.NOTICE_ID;

public class DeleteNoticeCommand implements Command {
    private static Logger logger = LogManager.getLogger();
    private NoticeService noticeService = NoticeServiceImpl.getInstance();

        @Override
        public String execute(HttpServletRequest request) throws CommandException {
            int noticeId = Integer.parseInt(request.getParameter(NOTICE_ID));
            try {
                boolean deletedNotice = noticeService.deleteNotice(noticeId);
                if (deletedNotice) {
                    request.setAttribute(MESSAGE, "Notice with ID " + noticeId + " has been deleted");
                    List<Notice> noticeList = noticeService.findAllNotice();
                    request.setAttribute(NOTICE_LIST, noticeList);
                } else {
                    request.setAttribute(MESSAGE, "Failed to delete notice with ID " + noticeId);
                }
            } catch (ServiceException e) {
                logger.error("Failed to delete notice");
                throw new CommandException("Failed to delete notice" + e.getMessage());
            }
            return "/pages/notices.jsp";
    }
}
