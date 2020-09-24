package com.water76016.ourtask.controller;

import com.water76016.ourtask.common.RestResult;
import com.water76016.ourtask.config.security.jwt.JwtAuthService;
import com.water76016.ourtask.entity.Tourist;
import com.water76016.ourtask.entity.User;
import com.water76016.ourtask.service.TouristService;
import com.water76016.ourtask.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
/**
 * @program: our-task
 * @description: 针对游客能够调用接口的控制器
 * @author: water76016
 * @create: 2020-09-24 16:45
 **/
public class TouristController {
    @Autowired
    TouristService touristService;


    @ApiOperation("游客进行注册操作")
    @PostMapping("/register")
    public RestResult register(@RequestBody Tourist tourist){
        return touristService.register(tourist);
    }

    /**
     * 登录方法
     *
     */
    @ApiOperation("游客进行登录操作")
    @PostMapping({"/login", "/"})
    public RestResult login(@RequestBody Tourist tourist, HttpServletResponse response) {
        return touristService.login(tourist, response);
    }

}
