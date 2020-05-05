package com.example.demo.controller;

import com.example.demo.entity.Task;
import com.example.demo.mapper.TaskMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("our-task")
public class TaskController {
    @Autowired(required = false)
    private TaskMapper taskMapper;

    /**
     * 添加一个新的清单
     * */
    @PostMapping("addTask")
    void addTask(Integer categoryId, String name){
        taskMapper.addTask(categoryId, name);
    }

    @PostMapping("deleteTaskById")
    void deleteTaskById(Integer id){
        taskMapper.deleteTaskById(id);
    }

    /**
     * 对清单进行更新
     * */
    @PostMapping("updateTask")
    void updateTask(Integer id, Integer categoryId, String name, String description, Integer run){
        Task task = new Task(id, categoryId, name, description, run);
        taskMapper.updateTask(task);
    }

    /**
     * 根据条件查询某些清单
     * */
    @GetMapping("getTaskList")
    List<Task> getTaskList(Integer categoryId, Integer run){
        Task task = new Task(categoryId, run);
        List<Task> result = taskMapper.getTaskList(task);
        return result;
    }
}
