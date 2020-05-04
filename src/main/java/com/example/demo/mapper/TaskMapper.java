package com.example.demo.mapper;

import com.example.demo.entity.Task;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TaskMapper {
    /**
     * 添加一个新的清单
     * */
    void addTask(Integer categoryId, String name);
    /**
     * 通过id删除一个清单
     * */
    void deleteTaskById(Integer id);
    /**
     * 对清单信息进行更新
     * */
    void updateTask(Task task);
    /**
     * 根据条件查询出清单
     * */
    List<Task> getTaskList(Task task);

}
