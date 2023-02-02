package com.yt.javaweb;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("这是一个get请求");
        ServletConfig config = getServletConfig();
        String userName = config.getInitParameter("userName");
        System.out.println(userName);
        ServletContext servletContext = config.getServletContext();
        String password = servletContext.getInitParameter("password");
        System.out.println(password);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        System.out.println("这是一个post请求");
        String userName = req.getParameter("userName");
        System.out.println(userName);
        String password = req.getParameter("password");
        System.out.println(password);
    }
}
