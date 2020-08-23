package com.water76016.ourtask.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.water76016.ourtask.entity.Task;
import com.water76016.ourtask.mapper.TaskMapper;
import com.water76016.ourtask.service.TaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author github:water76016
 * @since 2020-07-21
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {

    @Override
    public Integer countTask(Integer userId, Integer categoryId) {
        QueryWrapper<Task> taskQueryWrapper = new QueryWrapper<>();
        taskQueryWrapper.eq("user_id", userId);
        taskQueryWrapper.eq("category_id", categoryId);
        Integer countTask = count(taskQueryWrapper);
        return countTask;
    }
}
