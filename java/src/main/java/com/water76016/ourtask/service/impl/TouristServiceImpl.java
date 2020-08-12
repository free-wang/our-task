package com.water76016.ourtask.service.impl;

import com.water76016.ourtask.common.RestResult;
import com.water76016.ourtask.config.security.jwt.JwtAuthService;
import com.water76016.ourtask.entity.Tourist;
import com.water76016.ourtask.entity.User;
import com.water76016.ourtask.service.TouristService;
import com.water76016.ourtask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
public class TouristServiceImpl implements TouristService {
    @Autowired
    UserService userService;

    @Autowired
    JwtAuthService jwtAuthService;

    /**
     * 游客注册服务
     * */
    @Override
    public RestResult register(Tourist tourist){
        String username = tourist.getUsername();
        String password = tourist.getPassword();
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
     *游客登录服务
     */
    @Override
    public RestResult login(Tourist tourist, HttpServletResponse response) {
        RestResult result = RestResult.success();
        String token = jwtAuthService.login(tourist.getUsername(), tourist.getPassword());
        result.put("token", token);
        response.setHeader("Authorization", token);
        return result;
    }
}
