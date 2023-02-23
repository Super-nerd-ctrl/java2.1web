package com.yt.ssm.dto;

import lombok.Data;

/**
 * @author xiaoyu
 * @date 2023/2/23
 * @apiNote
 */
@Data
public class RegisterUserDto {
    /**
     * id
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 确认密码
     */
    private String requirePwd;
    /**
     * 年龄
     */
    private int age;

    /**
     * 性别 0-女 1-男
     */
    private int gender;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话
     */
    private String phone;


}
