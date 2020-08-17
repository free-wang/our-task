package com.water76016.ourtask.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.water76016.ourtask.common.RestResult;
import com.water76016.ourtask.dto.LabelParam;
import com.water76016.ourtask.entity.Label;
import com.water76016.ourtask.entity.TaskLabel;
import com.water76016.ourtask.service.LabelService;
import com.water76016.ourtask.service.TaskLabelService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
        Label label = new Label();
        label.setId(id);
        label.setRun(0);
        labelService.updateById(label);
        return RestResult.success("已经成功删除一个标签");
    }

}
