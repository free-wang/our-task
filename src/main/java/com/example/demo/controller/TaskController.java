package com.example.demo.controller;

import com.example.demo.entity.Task;
import com.example.demo.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("our-task")
public class TaskController {
    @Autowired(required = false)
    TaskMapper taskMapper;

    @GetMapping("updateTask")
    void updateTask(Integer id, Integer categoryId, String name, String description, Integer run){
        Task task = new Task(id, categoryId, name, description, run);
        taskMapper.updateTask(task);
    }

    @GetMapping("getTaskList")
    List<Task> getTaskList(Integer categoryId, Integer run){
        Task task = new Task(categoryId, run);
        List<Task> result = taskMapper.getTaskList(task);
        return result;
    }
}
