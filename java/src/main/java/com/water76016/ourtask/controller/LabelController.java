package com.water76016.ourtask.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.water76016.ourtask.common.RestResult;
import com.water76016.ourtask.entity.Label;
import com.water76016.ourtask.entity.Task;
import com.water76016.ourtask.service.LabelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author github:water76016
 * @since 2020-08-15
 */

@RestController
@RequestMapping("/label")
public class LabelController {
    @Autowired
    LabelService labelService;

    @ApiOperation("查询当前用户的所有标签")
    @GetMapping("getAllList/{userId}")
    public RestResult getAllList(@PathVariable("userId") Integer userId){
        QueryWrapper<Label> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("run", 1);
        List<Label> labelList = labelService.list(queryWrapper);
        return RestResult.success("得到当前用户所有正在使用的标签成功", labelList);
    }
}
