package com.yt.ssm.domin;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xiaoyu
 * @date 2023/2/23
 * @apiNote 用户实体
 */
@Data
public class User implements Serializable {

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

    /**
     * 是否为管理员
     * 1-管理员 0-普通用户
     */
    private int isAdmin;

}
