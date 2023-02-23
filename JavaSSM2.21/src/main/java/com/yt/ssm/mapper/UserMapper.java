package com.yt.ssm.mapper;

import com.yt.ssm.domin.User;
import com.yt.ssm.dto.ConditionQueryUserDto;
import com.yt.ssm.dto.RegisterUserDto;
import com.yt.ssm.dto.UserDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xiaoyu
 * @date 2023/2/23
 * @apiNote
 */
public interface UserMapper {
    /**
     * 根据用户名查询user
     * @param username
     * @return
     */
    int getUserByUsername(@Param("username") String username);

    /**
     * 根据id查询user
     * @param id
     * @return
     */
    User getUserById(@Param("id") int id);

    /**
     * 根据用户名和密码查询user
     * @param username
     * @param password
     * @return
     */
    User getUser(@Param("username") String username, @Param("password") String password);

    /**
     * 查询所有用户
     * @return
     */
    List<User> getAllUsers();

    /**
     * 添加用户
     * @param registerUserDto
     * @return
     */
    int insertUser(@Param("registerUserDto") RegisterUserDto registerUserDto);

    /**
     * 删除用户
     * @param id
     * @return
     */
    int deleteUser(@Param("id") int id);

    /**
     * 动态模糊查询
     * @param userDto
     * @return
     */
    List<User> conditionQueryUser(@Param("userDto") ConditionQueryUserDto userDto);

    /**
     * 根据id修改用户信息
     * @param registerUserDto
     * @return
     */
    int updateUserById(@Param("registerUserDto") RegisterUserDto registerUserDto);
}
