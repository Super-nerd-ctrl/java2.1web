package com.yt.javaweb.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RewordServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("rewordServlet被调用");
        req.setAttribute("email", "1390855466@qq.com");
        req.getRequestDispatcher("/response").forward(req, resp);
    }
}
