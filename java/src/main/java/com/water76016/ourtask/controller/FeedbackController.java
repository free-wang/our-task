package com.water76016.ourtask.controller;


import com.water76016.ourtask.common.RestResult;
import com.water76016.ourtask.entity.Category;
import com.water76016.ourtask.entity.Feedback;
import com.water76016.ourtask.service.FeedbackService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author github:water76016
 * @since 2020-09-07
 */
@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;

    @ApiOperation("增加一条反馈信息")
    @PostMapping("add")
    public RestResult add(@RequestBody Feedback feedback){
        Feedback feed = new Feedback(feedback.getUserId(), feedback.getTitle(), feedback.getDescription());
        boolean flag = feedbackService.save(feed);
        if (flag){
            return RestResult.success();
        }
        return RestResult.error();
    }
}
