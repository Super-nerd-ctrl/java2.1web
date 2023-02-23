package com.yt.test.service;

import com.yt.test.dto.Result;
import com.yt.test.dto.UserDto;

import javax.servlet.http.HttpSession;

/**
 * @author xiaoyu
 * @date 2023/2/15
 * @apiNote
 */
public interface HelloService {
    Result login(UserDto userDto, HttpSession session);
}
