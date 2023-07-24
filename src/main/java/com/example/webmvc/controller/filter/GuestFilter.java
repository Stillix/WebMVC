package com.example.webmvc.controller.filter;

import com.example.webmvc.entity.UserRoleEnum;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

import static com.example.webmvc.command.SessionAttributeName.USER_ROLE;

@WebFilter(filterName = "StartFilter", urlPatterns = {"/controller"})
public class GuestFilter  implements Filter{
    private static Logger logger = LogManager.getLogger();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug("****************************************");
        logger.debug("***Start Filter Servlet initialized***");
        logger.debug("****************************************");
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        UserRoleEnum role = (UserRoleEnum) session.getAttribute(USER_ROLE);
        if (role == null) {
            role = UserRoleEnum.GUEST;
            session.setAttribute(USER_ROLE, role);
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/pages/notices.jsp");
            dispatcher.forward(req, resp);
            return;
        }
        chain.doFilter(request, response);
    }
}
