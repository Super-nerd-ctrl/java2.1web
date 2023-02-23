package com.yt.test.service.impl;

import com.yt.test.dto.Result;
import com.yt.test.dto.UserDto;
import com.yt.test.service.HelloService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * @author xiaoyu
 * @date 2023/2/15
 * @apiNote
 */
@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public Result login(UserDto userDto, HttpSession session) {
        String userName = userDto.getUserName();
        String password = userDto.getPassword();
        String reqCodeText = userDto.getReqCodeText();
        Object code = session.getAttribute("code");
        String codeString = code.toString();
        System.out.println(codeString);
        if (!("admin".equals(userName) && "123456".equals(password))) {
            return Result.fail("用户名或密码错误");
        }
        if (!codeString.equalsIgnoreCase(reqCodeText)) {
            return Result.fail("验证码错误");
        }
        return Result.ok(userDto);
    }
}
