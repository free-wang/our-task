package com.example.demo.mapper;

import com.example.demo.entity.Task;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TaskMapper {
    /**
     * 添加一个新的清单
     * */
    void addTask(Task task);
    /**
     * 通过id删除一个清单(逻辑删除)
     * */
    void deleteTaskById(Integer id);
    /**
     * 通过userId和categoryId删除该用户分类下所有的清单(逻辑删除)
     * */
    void deleteTaskByUserIdAndCategoryId(Integer userId, Integer categoryId);
    /**
     * 对清单信息进行更新
     * */
    void updateTask(Task task);
    /**
     * 根据条件查询出清单
     * */
    List<Task> getTaskList(Task task);

}
