package com.example.webmvc.controller.filter;

import com.example.webmvc.entity.User;
import com.example.webmvc.entity.UserRoleEnum;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

//@WebFilter(dispatcherTypes = { DispatcherType.FORWARD }, urlPatterns = { "/pages/admin/*" } )
public class AdminForwardFilter  {
    private static Logger logger = LogManager.getLogger();



    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException  {
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        User user = (User) httpRequest.getSession().getAttribute("user");
//        if(user == null || !user.getUserRole().equals(UserRoleEnum.ADMIN))  {
//            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/webapp/index.jsp");
//            dispatcher.forward(request, response);
//        }
//        chain.doFilter(request, response);
    }


}
