package com.yt.ssm.service.impl;

import com.github.pagehelper.Page;
import com.mysql.cj.util.StringUtils;
import com.yt.ssm.common.Result;
import com.yt.ssm.domin.User;
import com.yt.ssm.dto.ConditionQueryUserDto;
import com.yt.ssm.dto.RegisterUserDto;
import com.yt.ssm.dto.UserDto;
import com.yt.ssm.mapper.UserMapper;
import com.yt.ssm.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author xiaoyu
 * @date 2023/2/23
 * @apiNote
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Override
    public Result login(UserDto userDto, HttpServletRequest request) {
        //验证用户名范围
        if (userDto.getUsername().length() < 5 || userDto.getUsername().length() > 10) {
            return Result.fail("请输入用户名5-30位");
        }
        //验证密码范围
        if (userDto.getPassword().length() < 6) {
            return Result.fail("密码长度至少为六位");
        }
        //验证验证码是否正确
        String code = (String)request.getSession().getAttribute("code");
        if (!code.equalsIgnoreCase(userDto.getReqCodeText())) {
            return Result.fail("验证码输入不正确");
        }
        //查询数据库中是否有该用户
        User user = userMapper.getUser(userDto.getUsername(), userDto.getPassword());
        if (user == null) {
            return Result.fail("用户不存在");
        }
        int isAdmin = user.getIsAdmin();
        userDto.setIsAdmin(isAdmin);
        Integer id = user.getId();
        userDto.setId(id);
        request.getSession().setAttribute("loginUser", userDto);
        System.out.println(userDto);
        return Result.ok("登录成功！");
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userMapper.getAllUsers();
        return users;
    }

    @Override
    public Result register(RegisterUserDto registerUserDto, HttpServletRequest request) {
        if (!isAdmin(request)) {
            return Result.fail("您不是管理员");
        }

        Result check = check(registerUserDto);
        if (check == null) {
            return Result.fail("发生错误");
        }
        if (check.getCode() == 9999) {
            return check;
        }
        int count = userMapper.getUserByUsername(registerUserDto.getUsername());
        if (count > 0) {
            return Result.fail("用户已经存在");
        }
        int i = userMapper.insertUser(registerUserDto);
        if (i != 1) {
            return Result.fail("添加失败");
        }
        return Result.ok("添加成功");
    }

    /**
     * 校验
     * @param registerUserDto
     * @return
     */
    private Result check(RegisterUserDto registerUserDto) {
        //验证用户名范围
        if (registerUserDto.getUsername().length() < 5 || registerUserDto.getUsername().length() > 20) {
            return Result.fail("请输入用户名5-20位");
        }
        //验证密码范围
        if (registerUserDto.getPassword().length() < 6) {
            return Result.fail("密码长度至少为六位");
        }
        if (!registerUserDto.getPassword().equals(registerUserDto.getRequirePwd())) {
            return Result.fail("两次密码输入不一致");
        }
        if (registerUserDto.getGender() < 0 || registerUserDto.getGender() > 1) {
            return Result.fail("性别输入不正确");
        }
        if (registerUserDto.getAge() < 0 || registerUserDto.getAge() > 100) {
            return Result.fail("年龄输入不正确");
        }

        return Result.ok();
    }


    @Override
    public boolean isAdmin(HttpServletRequest request) {
        UserDto loginUser = (UserDto)request.getSession().getAttribute("loginUser");

        return loginUser != null && loginUser.getIsAdmin() == 1;
    }

    @Override
    public Result deleteUser(int id) {
        int i = userMapper.deleteUser(id);
        if (i != 1) {
            return Result.fail("删除失败");
        }
        return Result.ok("删除成功");
    }

    @Override
    public List<User> conditionQueryUser(ConditionQueryUserDto userDto) {

        List<User> users = userMapper.conditionQueryUser(userDto);

        return users;
    }


    @Override
    public User getUserById(int id) {
        return userMapper.getUserById(id);
    }

    @Override
    public Result updateUserById(RegisterUserDto registerUserDto) {
        Result check = check(registerUserDto);
        if (check == null) {
            return Result.fail("发生错误");
        }
        if (check.getCode() == 9999) {
            return check;
        }
        int i = userMapper.updateUserById(registerUserDto);
        if (i != 1) {
            return Result.fail("修改失败");
        }
        return Result.ok("修改成功");
    }


}
