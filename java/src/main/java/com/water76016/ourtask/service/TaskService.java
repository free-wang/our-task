package com.water76016.ourtask.service;

import com.water76016.ourtask.entity.Task;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author github:water76016
 * @since 2020-07-21
 */
public interface TaskService extends IService<Task> {
    /**
     * 根据分类id，找到所含的清单总数
     * */
    public Integer countTask(Integer userId, Integer categoryId);

}
