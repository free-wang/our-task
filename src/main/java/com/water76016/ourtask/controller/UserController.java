package com.water76016.ourtask.controller;


import com.water76016.ourtask.common.RestResult;
import com.water76016.ourtask.entity.User;
import com.water76016.ourtask.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author github:water76016
 * @since 2020-07-21
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @ApiOperation("修改用户基本信息")
    @PostMapping("update/{id}")
    public RestResult update(@PathVariable("id") Integer id, String username, String telephone, String email){
        User user = new User(id, username, telephone, email);
        userService.updateById(user);
        return RestResult.success("更新用户成功");
    }

    @ApiOperation("根据id返回一个用户信息")
    @GetMapping("/get/{id}")
    public RestResult get(@PathVariable Integer id){
        User user = userService.getById(id);
        user.setPassword("******");
        return RestResult.success("查询用户成功", user);
    }

    @ApiOperation("修改用户密码")
    @PostMapping("updatePassword/{id}")
    public RestResult updatePassword(@PathVariable("id") Integer id, String oldPassword, String newPassword){
        //先判断用户名和密码是否匹配
        BCryptPasswordEncoder bcp = new BCryptPasswordEncoder();
        User user = userService.getById(id);
        if (bcp.matches(oldPassword, user.getPassword()) == false){
            return RestResult.error("用户名和密码不匹配");
        }

        String password = bcp.encode(newPassword);
        user.setPassword(password);
        userService.updateById(user);
        return RestResult.success("修改用户密码成功");
    }
}
