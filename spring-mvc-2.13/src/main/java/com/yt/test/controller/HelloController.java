package com.yt.test.controller;

import com.wf.captcha.GifCaptcha;
import com.yt.test.dto.Result;
import com.yt.test.dto.UserDto;
import com.yt.test.service.HelloService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author xiaoyu
 * @date 2023/2/13
 * @apiNote
 */
@Controller
@RequestMapping("/test")
public class HelloController {

    @Resource
    private HelloService helloService;

    @GetMapping(value = "/login/")
    public String index() {
        return "main";
    }

    @PostMapping("register")
    @ResponseBody
    public Result register(@RequestBody UserDto userDto, HttpSession session) {

        return helloService.login(userDto, session);
    }

    @GetMapping("/code")
    @ResponseBody
    public Result getCode(HttpSession session) {
        //创建验证码对象
        GifCaptcha gifCaptcha = new GifCaptcha(130,48,4);
        String text = gifCaptcha.text();
        session.setAttribute("code", text);
        String s = gifCaptcha.toBase64();
        return Result.ok(s);
    }

    @GetMapping("/login/xxx")
    public String toXXX() {
        return "xxx";
    }
}
