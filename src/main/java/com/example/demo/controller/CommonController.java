package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.entity.Task;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.mapper.TaskMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CommonController {
    /**
     *index初始页面
     * */
    @Autowired(required = false)
    CategoryMapper categoryMapper;
    @Autowired(required = false)
    TaskMapper taskMapper;
    @RequestMapping("index")
    public String index(Model model, @Param("categoryId") Integer categoryId){
        //得到所有的分类
        List<Category> categoryList = new ArrayList<>();
        categoryList = categoryMapper.getCategoryList();
        model.addAttribute("categoryList", categoryList);
        //如果没有输入的话，默认显示今天的清单
        List<Task> taskList = new ArrayList<>();
        Task taskCondition = new Task();
        //把当前查询的那个分类传递给前台
        Category currentCategory = null;
        if (categoryId == null){
            taskCondition = new Task(1, 1);
            currentCategory = categoryMapper.getCategoryById(1);
        }
        else {
            currentCategory = categoryMapper.getCategoryById(categoryId);
            taskCondition = new Task(categoryId, 1);
        }
        model.addAttribute("currentCategory", currentCategory);
        taskList = taskMapper.getTaskList(taskCondition);
        model.addAttribute("taskList", taskList);
        return "index";
    }
}
