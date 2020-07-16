package com.water76016.ourtask.service.impl;

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
 * @since 2020-07-16
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {

}
