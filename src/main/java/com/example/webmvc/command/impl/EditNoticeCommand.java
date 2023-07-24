package com.example.webmvc.command.impl;

import com.example.webmvc.command.Command;
import com.example.webmvc.entity.Notice;
import com.example.webmvc.entity.User;
import com.example.webmvc.exception.CommandException;
import com.example.webmvc.exception.ServiceException;
import com.example.webmvc.service.NoticeService;
import com.example.webmvc.service.impl.NoticeServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static com.example.webmvc.command.SessionAttributeName.*;


public class EditNoticeCommand implements Command {
    private static Logger logger = LogManager.getLogger();
    private NoticeService noticeService = NoticeServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        int noticeId = Integer.parseInt(request.getParameter(NOTICE_ID));
        Optional<Notice> notice = null;
        try {
            notice = noticeService.findNoticeByNoticeId(noticeId);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute(NOTICE, notice.get());
        return "/pages/change_notice_info.jsp";
    }
}
