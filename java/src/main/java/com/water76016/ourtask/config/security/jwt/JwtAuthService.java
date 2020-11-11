package com.water76016.ourtask.config.security.jwt;

import com.water76016.ourtask.common.Utils;
import com.water76016.ourtask.entity.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.swing.*;

/**
 * @program: our-task
 * @description: JWT服务类
 * @author: water76016
 * @create: 2020-09-24 16:45
 **/
@Service
public class JwtAuthService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    /**
     * 登录认证换取JWT令牌
     * @param username 用户名
     * @param password 密码
     * @return 结果
     */
    public String login(String username, String password)  {
        // 用户验证
        Authentication authentication = null;
        try {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new
                    UsernamePasswordAuthenticationToken(username, password);
            authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        }
        catch (Exception e) {
                throw new RuntimeException("用户名或密码错误");
        }
        User loginUser = (User) authentication.getPrincipal();
        // 生成token
        return jwtTokenUtil.generateToken(loginUser);
    }
}
