package com.yt.javaweb.controller;

import com.alibaba.fastjson.JSON;
import javafx.application.Application;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("views/login.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        System.out.println("这是一个post请求");
        String userName = req.getParameter("userName");
        System.out.println(userName);
        String password = req.getParameter("password");
        System.out.println(password);
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/json;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        Map<String, Object> data= new HashMap<String, Object>();

        String reqCodeText = req.getParameter("reqCodeText");
        Object reqCode = req.getSession().getAttribute("reqCode");
        if (reqCode == null) {
            data.put("code","9998");
            data.put("msg","验证码过期从新填写");
        } else {
            String reqCodeStr = (String)reqCode;

            if (reqCodeStr.equalsIgnoreCase(reqCodeText) && "admin".equals(userName) && "123456".equals(password)) {
                data.put("code","0000");
                data.put("msg","登录成功！");
                req.getSession().setAttribute("userName", userName);
            }else {
                data.put("code", "9999");
                data.put("msg", "登录失败");
                }
        }
        String s = JSON.toJSONString(data);
        writer.println(s);
        writer.flush();
        writer.close();
    }
}
