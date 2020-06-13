package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * 添加一个新的用户
     * */
    void addUser(User user);

    /**
     * 根据用户id删除一个用户（逻辑删除）
     * */
    void deleteUserById(Integer id);
    /**
     * 对用户信息进行更新
     * */
    void updateUser(User user);
    /**
     * 根据用户id查询当前用户对象
     * */
    User getUserById(Integer id);
    /**
     * 查询当前系统下的用户，包括（查询管理员用户、普通用户或者所有用户）
     * */
    List<User> getUserListByAuthority(@Param("authority") Integer authority);
}
