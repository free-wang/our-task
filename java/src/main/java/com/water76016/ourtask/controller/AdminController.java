package com.water76016.ourtask.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.water76016.ourtask.common.RestResult;
import com.water76016.ourtask.entity.User;
import com.water76016.ourtask.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 这是管理员操作的Controller
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    /**
     * The User service.
     */
    @Autowired
    UserService userService;

    /**
     * Freeze user by id rest result.
     *
     * @param id the id
     * @return the rest result
     */
    @ApiOperation("管理员冻结一个用户")
    @GetMapping("/freezeUser/{id}")
    public RestResult freezeUserById(@PathVariable Integer id){
        userService.removeById(id);
        return new RestResult(200, "冻结用户成功");
    }

    /**
     * List all user rest result.
     *
     * @return the rest result
     */
    @ApiOperation("查询所有用户信息")
    @GetMapping("listAllUser")
    public RestResult listAllUser(){
        List<User> userList = userService.list();
        return RestResult.success("查询所有用户信息成功", userList);
    }

    /**
     * List user by status rest result.
     *
     * @param status the status
     * @return the rest result
     */
    @ApiOperation("查询所有冻结/未冻结的用户信息")
    @GetMapping("listUser/{status}")
    public RestResult listUserByStatus(@PathVariable Integer status){
        if (status != 0 && status != 1){
            return RestResult.error("错误的传值");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("status", status);
        List<User> userList = (List<User>)userService.listByMap(map);
        return RestResult.success("查询成功", userList);
    }
}
