package com.water76016.ourtask.controller;


import com.water76016.ourtask.common.RestResult;
import com.water76016.ourtask.entity.Category;
import com.water76016.ourtask.entity.Feedback;
import com.water76016.ourtask.service.FeedbackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * @author github:water76016
 * @since 2020-09-07
 */
@Api(value = "反馈控制", tags = {"反馈操作"})
@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;

    @ApiOperation("增加一条反馈信息")
    @PostMapping("add")
    public RestResult add(@RequestBody @ApiParam("反馈对象") Feedback feedback){
        boolean flag = feedbackService.save(feedback);
        return flag ? RestResult.success() : RestResult.error();
    }
}
