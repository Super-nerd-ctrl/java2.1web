package com.yt.ssm.dto;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xiaoyu
 * @date 2023/2/23
 * @apiNote
 */
@Data
public class ConditionQueryUserDto implements Serializable {

    private String userIdStr;

    private String username;

    private String phone;

}
