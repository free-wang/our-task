package com.water76016.ourtask.controller;

import com.water76016.ourtask.common.RestResult;
import com.water76016.ourtask.config.security.jwt.JwtAuthService;
import com.water76016.ourtask.entity.User;
import com.water76016.ourtask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
/**
 * 这是针对游客访问的Controller
 * */
public class TouristController {
    @Autowired
    UserService userService;

    @Autowired
    JwtAuthService jwtAuthService;


    @PostMapping("/register")
    public RestResult register(@RequestParam("username") String username,@RequestParam("password") String password){
        User user = new User();
        user.setUsername(username);
        //加密需要把用户密码进行加密存储
        BCryptPasswordEncoder bcp = new BCryptPasswordEncoder();
        String encodePassword = bcp.encode(password);
        user.setPassword(encodePassword);
        boolean flag = userService.save(user);
        if (flag){
            return RestResult.success("添加用户成功");
        }
        return RestResult.error("添加用户失败");
    }

    /**
     * 登录方法
     *
     * @param username 用户名
     * @param password 密码
     * @return 结果
     */
    @PostMapping({"/login", "/"})
    public RestResult login(@RequestParam("username") String username,@RequestParam("password") String password) {
        RestResult result = RestResult.success();
        String token = jwtAuthService.login(username, password);
        result.put("token", token);
        return result;
    }

}
