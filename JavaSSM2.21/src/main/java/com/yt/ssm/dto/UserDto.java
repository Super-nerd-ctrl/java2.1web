package com.yt.ssm.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.SplittableRandom;

/**
 * @author xiaoyu
 * @date 2023/2/23
 * @apiNote
 */
@Data
public class UserDto implements Serializable {

    private int id;

    private String username;

    private String password;

    private int isAdmin;

    private String reqCodeText;
}
