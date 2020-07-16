package com.water76016.ourtask.controller;

import com.water76016.ourtask.common.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonController {
    @Autowired
    JwtUtils jwtUtils;

    @GetMapping("/hello")
    public Claims hello(){
        Long userId = 123L;
        String one = jwtUtils.generateToken(userId);
        return jwtUtils.getClaimByToken(one);
    }
}
