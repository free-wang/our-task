package com.water76016.ourtask.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.water76016.ourtask.common.RestResult;
import com.water76016.ourtask.dto.TaskParam;
import com.water76016.ourtask.entity.Category;
import com.water76016.ourtask.entity.Task;
import com.water76016.ourtask.entity.TaskLabel;
import com.water76016.ourtask.entity.User;
import com.water76016.ourtask.service.LabelService;
import com.water76016.ourtask.service.TaskLabelService;
import com.water76016.ourtask.service.TaskService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.time.LocalDateTime;
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

    @Autowired
    TaskLabelService taskLabelService;

    @ApiOperation("添加一个新的清单")
    @PostMapping("/save")
    public RestResult save(@RequestBody TaskParam taskParam){
        Task task = new Task(taskParam.getUserId(), taskParam.getCategoryId(), taskParam.getName(),
                taskParam.getDescription());
        taskService.save(task);
        Integer taskId = task.getId();
        //准备往task_label表里面插值
        List<Integer> labelList = taskParam.getLabelList();
        for (Integer labelId : labelList){
            TaskLabel taskLabel = new TaskLabel(taskId, labelId);
            taskLabelService.save(taskLabel);
        }
        return RestResult.success();
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
    public RestResult updateTask(@PathVariable("id") Integer id, @RequestBody Task task){
        taskService.updateById(task);
        return RestResult.success("更新清单成功");
    }

    @ApiOperation("查询当前用户的所有清单")
    @GetMapping("getAllList/{userId}")
    public RestResult getAllList(@PathVariable("userId") Integer userId){
        QueryWrapper<Task> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("run", 1);
        List<Task> taskList = taskService.list(queryWrapper);
        return RestResult.success("得到当前用户所有未完成清单成功", taskList);
    }

    @ApiOperation("查询当前用户当前分类的所有未完成清单")
    @GetMapping("getAllList/{userId}/{categoryId}")
    public RestResult getTaskList(@PathVariable("userId") Integer userId, @PathVariable("categoryId") Integer categoryId){
        QueryWrapper<Task> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("category_id", categoryId);
        queryWrapper.eq("run", 1);
        List<Task> taskList = taskService.list(queryWrapper);
        //todo:这里返回的日期还需要修改
        return RestResult.success(taskList);
    }

    @ApiOperation("查询当前用户，当前页的所有未完成的清单")
    @GetMapping("/getPageList/{userId}")
    public RestResult getPageList(@PathVariable("userId") Integer userId,
                         @RequestParam(value = "pageCurrent", defaultValue = "1") @ApiParam("当前页") Integer pageCurrent,
                         @RequestParam(value = "pageSize", defaultValue = "3") @ApiParam("每页数量") Integer pageSize){
        QueryWrapper<Task> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("run", 1);
        Page<Task> page = new Page<>();
        page.setCurrent(pageCurrent);
        page.setSize(pageSize);
        IPage<Task> taskIPage = taskService.page(page, queryWrapper);
        return RestResult.success("得到当前分页清单成功", taskIPage);
    }
}
