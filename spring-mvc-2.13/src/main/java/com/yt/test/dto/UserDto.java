package com.yt.test.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xiaoyu
 * @date 2023/2/14
 * @apiNote
 */
@Data
public class UserDto implements Serializable {

    private String userName;

    private String password;

    private String reqCodeText;
}
