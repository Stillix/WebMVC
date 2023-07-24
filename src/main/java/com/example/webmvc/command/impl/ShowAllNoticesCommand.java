package com.example.webmvc.command.impl;

import com.example.webmvc.command.Command;
import com.example.webmvc.entity.Notice;
import com.example.webmvc.exception.CommandException;
import com.example.webmvc.exception.ServiceException;
import com.example.webmvc.service.NoticeService;
import com.example.webmvc.service.impl.NoticeServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.example.webmvc.command.RequestAttributeName.MESSAGE;
import static com.example.webmvc.command.RequestAttributeName.NOTICE_LIST;


public class ShowAllNoticesCommand implements Command {
    private static Logger logger = LogManager.getLogger();
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        NoticeService noticeService = NoticeServiceImpl.getInstance();
        try {
            List<Notice> noticeList = noticeService.findAllNotice();
            request.setAttribute(NOTICE_LIST, noticeList);
            if (!noticeList.isEmpty()) {
                request.setAttribute(NOTICE_LIST, noticeList);
                request.setAttribute(MESSAGE, "notices found: " + noticeList.size());
                logger.info("Notices found: " + noticeList.size());
            } else {
                logger.info("No notices found");
                request.setAttribute(MESSAGE, "No notices found");
            }
        } catch (ServiceException e) {
            throw new CommandException(e);

        }
        return "/pages/notices.jsp";
    }
}
