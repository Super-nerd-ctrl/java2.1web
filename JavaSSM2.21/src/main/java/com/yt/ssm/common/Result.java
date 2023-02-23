package com.yt.ssm.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author xiaoyu
 * @date 2023/2/22
 * @apiNote 返回结果类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Integer code;
    private String errorMsg;
    private Object data;
    private Long total;

    public static Result ok(){
        return new Result(0, null, null, null);
    }
    public static Result ok(Object data){
        return new Result(0, null, data, null);
    }
    public static Result ok(List<?> data, Long total){
        return new Result(0, null, data, total);
    }
    public static Result fail(String errorMsg){
        return new Result(9999, errorMsg, null, null);
    }
}
