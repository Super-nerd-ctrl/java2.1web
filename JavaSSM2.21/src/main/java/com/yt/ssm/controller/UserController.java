package com.yt.ssm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.wf.captcha.GifCaptcha;
import com.yt.ssm.common.Result;
import com.yt.ssm.domin.User;
import com.yt.ssm.dto.ConditionQueryUserDto;
import com.yt.ssm.dto.RegisterUserDto;
import com.yt.ssm.dto.UserDto;
import com.yt.ssm.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author xiaoyu
 * @date 2023/2/23
 * @apiNote
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 设置验证码
     * @param request
     * @return
     */
    @GetMapping("/code")
    @ResponseBody
    public Result getCode(HttpServletRequest request) {
        //创建验证码对象
        GifCaptcha gifCaptcha = new GifCaptcha(130,48,4);
        String text = gifCaptcha.text();
        request.getSession().setAttribute("code", text);
        String s = gifCaptcha.toBase64();
        return Result.ok(s);
    }

    /**
     * 登录接口
     * @param userDto
     * @param request
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestBody UserDto userDto, HttpServletRequest request) {
        System.out.println(userDto);
        return userService.login(userDto, request);
    }

    /**
     * 视图跳转
     * @return
     */
    @GetMapping("/main")
    public String main() {
        return "main";
    }

    @GetMapping("/toUpdate/main")
    public String toMain() {
        return "redirect:/user/main";
    }

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/allUsers/{page}/{pageSize}")
    @ResponseBody
    public Result getAllUsersByPage(@PathVariable("page") int page,@PathVariable("pageSize") int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<User> users = userService.getAllUsers();

        PageInfo<User> userPageInfo = new PageInfo<>(users, page);

        System.out.println(userPageInfo);

        return Result.ok(userPageInfo);
    }

    /**
     * 判断是否为管理员
     * @param request
     * @return
     */
    @GetMapping("/isAdmin")
    @ResponseBody
    public Result isAdmin(HttpServletRequest request) {
        boolean isTrue = userService.isAdmin(request);
        if (!isTrue) {
            return Result.fail("您不是管理员");
        }
        return Result.ok();
    }

    /**
     * 判断是否具有修改信息的条件
     * @param id
     * @return
     */
    @GetMapping("/isUpdate/{id}")
    @ResponseBody
    public Result isUpdate(@PathVariable("id") int id, HttpServletRequest request) {
        UserDto loginUser = (UserDto) request.getSession().getAttribute("loginUser");
        int loginUserId = loginUser.getId();
        boolean isTrue = userService.isAdmin(request);
        if (!isTrue && loginUserId != id) {
            return Result.fail("您不是管理员，只能修改自己账号的信息");
        }
        return Result.ok();
    }

    /**
     * 页面跳转
     * @return
     */
    @GetMapping("/toAdd")
    public String toAdd() {

        return "/register";
    }

    /**
     * 添加用户
     * @param request
     * @return
     */
    @PostMapping("/register")
    @ResponseBody
    public Result register(@RequestBody RegisterUserDto registerUserDto, HttpServletRequest request) {

        System.out.println(registerUserDto);
        return userService.register(registerUserDto,request);
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @DeleteMapping("/deleteUser/{id}")
    @ResponseBody
    public Result deleteUser(@PathVariable("id") int id) {

        return userService.deleteUser(id);
    }

    @GetMapping("/toUpdate/{id}")
    public String toUpdate(@PathVariable("id") int id, HttpServletRequest request) {
        User user = userService.getUserById(id);
        Gson gson = new Gson();
        String userStr = gson.toJson(user);
        request.getSession().setAttribute("user", userStr);

        return "updateUser";
    }

    /**
     * 模糊查询
     * @param userDto
     * @return
     */
    @PostMapping("/conditionQuery")
    @ResponseBody
    public Result conditionQueryUser(@RequestBody ConditionQueryUserDto userDto) {

        System.out.println(userDto);
        List<User> users = userService.conditionQueryUser(userDto);

        return Result.ok(users);
    }


    @PutMapping("")
    @ResponseBody
    public Result updateUserById(@RequestBody RegisterUserDto registerUserDto, HttpServletRequest request) {

        return userService.updateUserById(registerUserDto);
    }
}

