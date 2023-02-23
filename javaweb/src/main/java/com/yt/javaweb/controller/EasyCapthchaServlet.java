package com.yt.javaweb.controller;


import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EasyCapthchaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        //创建验证码对象
        GifCaptcha gifCaptcha = new GifCaptcha(130,48,4);

        String text = gifCaptcha.text();
        req.getSession().setAttribute("reqCode", text);

        String s = gifCaptcha.toBase64();
        ServletOutputStream outputStream = resp.getOutputStream();
        outputStream.println(s);
        outputStream.flush();
        outputStream.close();
    }
}
