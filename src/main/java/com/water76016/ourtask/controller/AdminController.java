package com.water76016.ourtask.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.water76016.ourtask.common.RestResult;
import com.water76016.ourtask.entity.User;
import com.water76016.ourtask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 这是管理员操作的Controller
 * */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserService userService;
    /**
     * 冻结一个用户
     * */
    @GetMapping("/freezeUserById")
    public RestResult freezeUserById(Integer id){
        User user = new User();
        user.setId(id);
        user.setStatus(0);
        userService.updateById(user);
        return new RestResult(200, "冻结用户成功");
    }
}
