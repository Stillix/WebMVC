package com.example.webmvc.controller;

import java.io.*;

import com.example.webmvc.command.Command;
import com.example.webmvc.command.CommandType;
import com.example.webmvc.exception.CommandException;
import com.example.webmvc.pool.ConnectionPool;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(name = "controller", urlPatterns = {"/controller", "*.do"})
public class Controller extends HttpServlet {
    private static Logger logger = LogManager.getLogger();

    public void init() {
        ConnectionPool.getInstance();
        logger.info("Servlet init" + this.getServletInfo());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String commandStr = request.getParameter("command");
        Command command = CommandType.defineCommand(commandStr);
        String page;
        try {
            page = command.execute(request);
            request.getRequestDispatcher(page).forward(request, response);
        } catch (CommandException e) {
            //log
            throw new ServletException(e.getMessage());

        }
    }

    public void destroy() {
        ConnectionPool.getInstance().destroyPool();
        logger.info("Servlet destroyed" + this.getServletName());
    }
}