package com.example.demo.controller;

import com.example.demo.entity.Task;
import com.example.demo.mapper.TaskMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("our-task")
public class TaskController {
    @Autowired(required = false)
    private TaskMapper taskMapper;

    /**
     * 添加一个新的清单
     * */
    @RequestMapping("addTask")
    void addTask(Integer userId, Integer categoryId, String name, String description){
        Task task = new Task(userId, categoryId, name, description);
        taskMapper.addTask(task);
//        return "redirect:/index?categoryId=" + categoryId;
    }

    @RequestMapping("deleteTaskById")
    void deleteTaskById(Integer id, Integer categoryId){
        taskMapper.deleteTaskById(id);
        //重定向到主页,并且带有分类的id，不然默认就会跳转到今天这个分类去了
//        return "redirect:/index?categoryId=" + categoryId;
    }

    /**
     * 对清单进行更新
     * */
    @RequestMapping("updateTask")
    void updateTask(Integer id, Integer userId,  Integer categoryId, String name, String description, Integer run){
        Task task = new Task(id, userId, categoryId, name, description, run);
        taskMapper.updateTask(task);
        //重定向到主页,并且带有分类的id，不然默认就会跳转到今天这个分类去了
//        return "redirect:/index?categoryId=" + categoryId;
    }

    /**
     * 根据条件查询某些清单
     * */
    @GetMapping("getTaskList")
    List<Task> getTaskList(Integer userId, Integer categoryId, Integer run){
        Task task = new Task(userId, categoryId, run);
        List<Task> result = taskMapper.getTaskList(task);
        return result;
    }
}
