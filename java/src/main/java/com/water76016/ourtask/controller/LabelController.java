package com.water76016.ourtask.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.water76016.ourtask.common.RestResult;
import com.water76016.ourtask.dto.LabelParam;
import com.water76016.ourtask.entity.Category;
import com.water76016.ourtask.entity.Label;
import com.water76016.ourtask.entity.TaskLabel;
import com.water76016.ourtask.service.LabelService;
import com.water76016.ourtask.service.TaskLabelService;
import com.water76016.ourtask.service.TaskService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author github:water76016
 * @since 2020-08-17
 */
@RestController
@RequestMapping("/label")
public class LabelController {
    @Autowired
    LabelService labelService;

    @Autowired
    TaskLabelService taskLabelService;

    @Autowired
    TaskService taskService;

    @ApiOperation("查询当前用户的所有标签")
    @GetMapping("listAll/{userId}")
    public RestResult getAllList(@PathVariable("userId") Integer userId){
        QueryWrapper<Label> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("run", 1);
        List<Label> labelList = labelService.list(queryWrapper);
        List<LabelParam> labelParamList = new ArrayList<>();
        for (Label label : labelList){
            Integer labelId = label.getId();
            QueryWrapper<TaskLabel> taskLabelQueryWrapper = new QueryWrapper<>();
            taskLabelQueryWrapper.eq("label_id", labelId);
            Integer count = taskLabelService.count(taskLabelQueryWrapper);
            LabelParam labelParam = new LabelParam(labelId, label.getName(), count);
            labelParamList.add(labelParam);
        }
        return RestResult.success(labelParamList);
    }

    @ApiOperation("逻辑删除/完成一个标签")
    @GetMapping("/delete/{id}")
    public RestResult deleteLabelById(@PathVariable("id") Integer id){
        labelService.removeById(id);
        return RestResult.success("已经成功删除一个标签");
    }

    @ApiOperation("修改标签的名称")
    @PostMapping("update/{id}")
    public RestResult update(@PathVariable("id") Integer id, @RequestBody Label label){
        labelService.updateById(label);
        return RestResult.success();
    }

    @ApiOperation("添加一个新的标签")
    @PostMapping("add")
    public RestResult add(@RequestBody Label label){
        labelService.save(label);
        return RestResult.success(label);
    }

    @ApiOperation("查询当前用户，当前页的标签列表")
    @GetMapping("/getPageList/{userId}/{pageCurrent}/{pageSize}")
    public RestResult getPageList(@PathVariable("userId") Integer userId,
                                  @PathVariable("pageCurrent") Integer pageCurrent,
                                  @PathVariable("pageSize") Integer pageSize){
        QueryWrapper<Label> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("run", 1);
        Page<Label> page = new Page<>();
        page.setCurrent(pageCurrent);
        page.setSize(pageSize);
        IPage<Label> labelPage = labelService.page(page, queryWrapper);
        for (Label label : labelPage.getRecords()){
            Integer labelId = label.getId();
            Integer countTask = taskService.countTask(userId, labelId);
            label.setTaskCount(countTask);
        }
        return RestResult.success("得到当前分页标签成功", labelPage);
    }



}
