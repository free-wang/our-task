package com.water76016.ourtask.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.water76016.ourtask.common.RestResult;
import com.water76016.ourtask.entity.Task;
import com.water76016.ourtask.entity.User;
import com.water76016.ourtask.service.TaskService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author github:water76016
 * @since 2020-07-21
 */
@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    TaskService taskService;

    @ApiOperation("添加一个新的清单")
    @PostMapping("/save")
    public RestResult save(Integer userId, Integer categoryId, String name){
        Task task = new Task(userId, categoryId, name);
        taskService.save(task);
        Map<String, Integer> data = new HashMap<>();
        data.put("userId", userId);
        data.put("categoryId", categoryId);
        return RestResult.success("新增清单成功", data);
    }

    @ApiOperation("逻辑删除/完成一个清单")
    @GetMapping("/delete/{id}")
    public RestResult deleteTaskById(@PathVariable("id") Integer id){
        Task task = new Task(id, 0);
        taskService.updateById(task);
        return RestResult.success("已经成功删除/完成一个清单");
    }

    @ApiOperation("对清单进行更新")
    @PostMapping("/update/{id}")
    public RestResult updateTask(@PathVariable("id") Integer id, Integer categoryId, String name, String description){
        Task task = new Task(id, categoryId, name, description);
        taskService.updateById(task);
        return RestResult.success("更新清单成功");
    }

    @ApiOperation("查询当前用户下未完成的所有清单")
    @GetMapping("getAllList/{userId}")
    public RestResult getAllList(@PathVariable("userId") Integer userId){
        QueryWrapper<Task> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("run", 0);
        List<Task> taskList = taskService.list(queryWrapper);
        return RestResult.success("得到当前用户所有未完成清单成功", taskList);
    }

    @ApiOperation("查询当前用户，当前页的所有未完成的清单")
    @GetMapping("/getPageList/{userId}")
    public RestResult getPageList(@PathVariable("userId") Integer userId,
                         @RequestParam(value = "pageCurrent", defaultValue = "1") @ApiParam("当前页") Integer pageCurrent,
                         @RequestParam(value = "pageSize", defaultValue = "3") @ApiParam("每页数量") Integer pageSize){
        QueryWrapper<Task> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("run", 0);
        Page<Task> page = new Page<>();
        page.setCurrent(pageCurrent);
        page.setSize(pageSize);
        IPage<Task> taskIPage = taskService.page(page, queryWrapper);
        return RestResult.success("得到当前分页清单成功", taskIPage);
    }
}
