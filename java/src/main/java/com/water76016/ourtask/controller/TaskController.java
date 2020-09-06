package com.water76016.ourtask.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.water76016.ourtask.common.RestResult;
import com.water76016.ourtask.dto.SelectCondition;
import com.water76016.ourtask.dto.Statistics;
import com.water76016.ourtask.dto.TaskParam;
import com.water76016.ourtask.entity.Task;
import com.water76016.ourtask.entity.TaskLabel;
import com.water76016.ourtask.service.TaskLabelService;
import com.water76016.ourtask.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.IconUIResource;
import javax.xml.transform.Result;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

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

    @ApiOperation("添加/更新一个清单")
    @PostMapping("/save")
    public RestResult saveOrUpdate(@RequestBody TaskParam taskParam){
        Task task = new Task(taskParam.getId(),taskParam.getUserId(), taskParam.getCategoryId(), taskParam.getName(),
                taskParam.getDescription());
        taskService.saveOrUpdate(task);
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
        List<TaskParam> taskParamList = new ArrayList<>();
        for (Task task : taskList){
            Integer taskId = task.getId();
            List<Integer> integerList = taskLabelService.getLableListByTaskId(taskId);
            TaskParam taskParam = new TaskParam();
            taskParam.setId(taskId);
            taskParam.setUserId(userId);
            taskParam.setName(task.getName());
            taskParam.setCategoryId(task.getCategoryId());
            taskParam.setLabelList(integerList);
            taskParam.setDescription(task.getDescription());
            taskParamList.add(taskParam);
        }
        return RestResult.success("得到当前用户所有未完成清单成功", taskParamList);
    }

    @ApiOperation("查询当前用户当前分类的所有未完成清单")
    @GetMapping("getAllList/{userId}/{categoryId}")
    public RestResult getTaskList(@PathVariable("userId") Integer userId, @PathVariable("categoryId") Integer categoryId){
        QueryWrapper<Task> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("category_id", categoryId);
        queryWrapper.eq("run", 1);
        List<Task> taskList = taskService.list(queryWrapper);
        return RestResult.success(taskList);
    }

    @ApiOperation("查询当前用户，当前页的所有未完成的清单，也可根据条件进行查询")
    @PostMapping("/getPageList/{userId}/{pageCurrent}/{pageSize}")
    public RestResult getPageList(@PathVariable("userId") Integer userId,
                                  @PathVariable("pageCurrent") Integer pageCurrent,
                                  @PathVariable("pageSize") Integer pageSize,
                                  @RequestBody SelectCondition selectCondition){
        QueryWrapper<Task> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("run", 1);
        String name = selectCondition.getName();
        Integer categoryId = selectCondition.getCategoryId();
        List<Integer> selectLabelIdList = selectCondition.getLabelIdList();
        if (StrUtil.hasEmpty(name) == false){
            queryWrapper.like("name", name).or().like("description", name);
        }
        if (categoryId != null){
            queryWrapper.eq("category_id", categoryId);
        }
        if (selectLabelIdList.size() > 0){
            List<Integer> idList = taskLabelService.getTaskIdListByLabelIdList(selectLabelIdList);
            if (idList != null && idList.size() > 0){
                queryWrapper.in("id", idList);
            }
        }
        Page<Task> page = new Page<>();
        page.setCurrent(pageCurrent);
        page.setSize(pageSize);
        IPage<Task> taskPage = taskService.page(page, queryWrapper);
        for (Task task : taskPage.getRecords()){
            Integer taskId = task.getId();
            List<Integer> labelIdList = taskLabelService.getLableListByTaskId(taskId);
            task.setLabelList(labelIdList);
        }
        return RestResult.success("得到当前分页清单成功", taskPage);
    }

    @ApiOperation("获取最近一周的清单完成情况")
    @GetMapping("/countTaskForDay/{userId}")
    public RestResult countTaskForDay(@PathVariable("userId") Integer userId){
        List<TreeMap<String, String>> result = taskService.countTaskForDay(userId);
        return RestResult.success(result);
    }


    @ApiOperation("获取最近七周的清单完成情况")
    @GetMapping("/countTaskForWeek/{userId}")
    public RestResult countTaskForWeek(@PathVariable("userId") Integer userId){
        List<TreeMap<String, String>> result = taskService.countTaskForWeek(userId);
        return RestResult.success(result);
    }

    @ApiOperation("获取用户清单的使用情况")
    @GetMapping("/getStatistics/{userId}")
    public RestResult getStatistics(@PathVariable("userId") Integer userId){
        Statistics statistics = taskService.getStatistics(userId);
        return RestResult.success(statistics);
    }
}
