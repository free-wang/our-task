package com.water76016.ourtask.controller;


import ch.qos.logback.core.pattern.util.RestrictedEscapeUtil;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author github:water76016
 * @since 2020-07-21
 */
@Api(value = "清单控制", tags = {"清单操作"})
@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    TaskService taskService;
    @Autowired
    TaskLabelService taskLabelService;

    @ApiOperation("添加/更新一个清单")
    @PostMapping("/save")
    public RestResult saveOrUpdate(@RequestBody @ApiParam("清单controller传输对象") TaskParam taskParam){
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
    public RestResult deleteTaskById(@PathVariable("id") @ApiParam("清单id") Integer id){
        Task task = taskService.getById(id);
        //todo:这里要弄一个判断，空指针异常
        task.setRun(0);
        taskService.updateById(task);
        return RestResult.success();
    }

    @ApiOperation("查询当前用户的所有清单")
    @GetMapping("getAllList/{userId}")
    public RestResult getAllList(@PathVariable("userId") @ApiParam("用户id") Integer userId){
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
        return RestResult.success(taskParamList);
    }

    @ApiOperation("查询当前用户当前分类的所有未完成清单")
    @GetMapping("getAllList/{userId}/{categoryId}")
    public RestResult getTaskList(@PathVariable("userId") @ApiParam("用户id") Integer userId,
                                  @PathVariable("categoryId") @ApiParam("分类id") Integer categoryId){
        QueryWrapper<Task> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("category_id", categoryId);
        queryWrapper.eq("run", 1);
        List<Task> taskList = taskService.list(queryWrapper);
        return taskList != null ? RestResult.success(taskList) : RestResult.error();
    }

    @ApiOperation("查询当前用户，当前页的所有未完成的清单，也可根据条件进行查询")
    @PostMapping("/getPageList/{userId}/{pageCurrent}/{pageSize}")
    public RestResult getPageList(@PathVariable("userId") @ApiParam("用户id") Integer userId,
                                  @PathVariable("pageCurrent") @ApiParam("当前页") Integer pageCurrent,
                                  @PathVariable("pageSize") @ApiParam("每页大小") Integer pageSize,
                                  @RequestBody SelectCondition selectCondition){
        QueryWrapper<Task> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("run", 1);
        String name = selectCondition.getName();
        Integer categoryId = selectCondition.getCategoryId();
        List<Integer> selectLabelIdList = selectCondition.getLabelIdList();
        if (StrUtil.hasEmpty(name) == false){
            queryWrapper.and(wrapper -> wrapper.like("name", name).or().like("description", name));
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
        return RestResult.success(taskPage);
    }

    @ApiOperation("获取最近一周的清单完成情况")
    @GetMapping("/countTaskForDay/{userId}")
    public RestResult countTaskForDay(@PathVariable("userId") @ApiParam("用户id") Integer userId){
        List<TreeMap<String, String>> result = taskService.countTaskForDay(userId);
        return RestResult.success(result);
    }

    @ApiOperation("获取最近七周的清单完成情况")
    @GetMapping("/countTaskForWeek/{userId}")
    public RestResult countTaskForWeek(@PathVariable("userId") @ApiParam("用户id") Integer userId){
        List<TreeMap<String, String>> result = taskService.countTaskForWeek(userId);
        return RestResult.success(result);
    }

    @ApiOperation("获取用户清单的使用情况")
    @GetMapping("/getStatistics/{userId}")
    public RestResult getStatistics(@PathVariable("userId") @ApiParam("用户id") Integer userId){
        Statistics statistics = taskService.getStatistics(userId);
        return RestResult.success(statistics);
    }

    @ApiOperation("查询用户在当天的每个分类清单的完成情况")
    @GetMapping("/countTodayForCategory/{userId}")
    public RestResult countTodayForCategory(@PathVariable("userId") @ApiParam("用户id") Integer userId){
        List<Map<String, String>> result = taskService.countTodayForCategory(userId);
        return RestResult.success(result);
    }
}
