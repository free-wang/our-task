package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired(required = false)
    UserMapper userMapper;

    /**
     * 添加一个新的用户
     * */
    @PostMapping("addUser")
    public String addUser(String telephone, String name, String password){
        User user = new User(telephone, name, password);
        userMapper.addUser(user);
        return "addUser success";
    }
    /**
     * 删除一个用户（逻辑删除，只是处于冻结状态）
     * */
    @GetMapping("deleteUserById")
    public String deleteUserById(Integer id){
        userMapper.deleteUserById(id);
        return "deleteUserById success";
    }

    /**
     * 对用户信息进行更新
     * */
    @PostMapping("updateUser")
    public String updateUser(String telephone, String name, String password, String email, String headPortrait,
                             Integer freeze){
        User user = new User(telephone, name, password, email, headPortrait, freeze);
        userMapper.updateUser(user);
        return "updateUser success";
    }

    /**
     * 根据用户id查询单个用户信息
     * */
    @GetMapping("getUserById")
    public User getUserById(Integer id){
        User user = userMapper.getUserById(id);
        return user;
    }

    /**
     * 根据用户权限查询用户：返回普通用户、管理员用户或全部用户
     * */
    @GetMapping("getUserListByAuthority")
    public List<User> getUserListByAuthority(Integer authority){
        List<User> userList = userMapper.getUserListByAuthority(authority);
        return userList;
    }


}
