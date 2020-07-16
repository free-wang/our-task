package com.water76016.ourtask.controller;


import com.water76016.ourtask.entity.User;
import com.water76016.ourtask.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author github:water76016
 * @since 2020-07-16
 */
@RestController
@RequestMapping("/user")
@Api
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("getUserList")
    public List<User> getUserList(){
        return userService.list();
    }
}
