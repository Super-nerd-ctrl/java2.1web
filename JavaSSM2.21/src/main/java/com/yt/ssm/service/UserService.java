package com.yt.ssm.service;

import com.yt.ssm.common.Result;
import com.yt.ssm.domin.User;
import com.yt.ssm.dto.ConditionQueryUserDto;
import com.yt.ssm.dto.RegisterUserDto;
import com.yt.ssm.dto.UserDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author xiaoyu
* @date 2023/2/23
* @apiNote
*/

public interface UserService {
    /**
     * 登录
     * @param userDto
     * @param request
     * @return
     */
    Result login(UserDto userDto, HttpServletRequest request);

    /**
     * 查询所有用户
     * @return
     */
    List<User> getAllUsers();

    /**
     * 用户添加
     * @param request
     * @return
     */
    Result register(RegisterUserDto registerUserDto, HttpServletRequest request);

    /**
     * 是否为管理员
     * @param request
     * @return
     */
    boolean isAdmin(HttpServletRequest request);

    Result deleteUser(int id);

    List<User> conditionQueryUser(ConditionQueryUserDto userDtoe);

    User getUserById(int id);

    Result updateUserById(RegisterUserDto registerUserDto);
}
