package com.example.webmvc.controller.filter;

import com.example.webmvc.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebFilter(filterName = "AccessFilter", urlPatterns = {"/*"})
public class AccessFilter implements Filter {
    private static Logger logger = LogManager.getLogger();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug("****************************************");
        logger.debug("***Access Filter Servlet initialized***");
        logger.debug("****************************************");
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//
//        HttpSession session = httpRequest.getSession();
//        String userRole = (String) session.getAttribute("userRole");
//
//        if (userRole == null) {
//            request.getRequestDispatcher( "/pages/authorization.jsp").forward(request, response);
//        } else {
//            String admin = "admin";
//            if (admin.equals(userRole)) {
//                httpResponse.sendRedirect("/admin/admin.jsp");
//            } else {
//                String client = "client";
//                if (client.equals(userRole)) {
//                    httpResponse.sendRedirect("/client/client.jsp");
//                }
//            }
//        }
//        chain.doFilter(request, response);
    }
}
