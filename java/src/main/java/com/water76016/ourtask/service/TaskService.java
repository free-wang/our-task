package com.water76016.ourtask.service;

import com.water76016.ourtask.dto.Statistics;
import com.water76016.ourtask.entity.Task;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author github :water76016
 * @since 2020 -07-21
 */
public interface TaskService extends IService<Task> {
    /**
     * 根据分类id，找到所含的清单总数
     *
     * @param userId     the user id
     * @param categoryId the category id
     * @return the integer
     */
    public Integer countTask(Integer userId, Integer categoryId);

    /**
     * 根据标签id，找到所含的清单总数
     *
     * @param userId  the user id
     * @param labelId the label id
     * @return the integer
     */
    public Integer countTaskByLabelId(Integer userId, Integer labelId);

    /**
     * 查询某个用户，最近七天的完成情况
     *
     * @param userId the user id
     * @return the list
     */
    public List<TreeMap<String, String>> countTaskForDay(Integer userId);

    /**
     * 查询某个用户，最近七周的完成情况
     *
     * @param userId the user id
     * @return the list
     */
    public List<TreeMap<String, String>> countTaskForWeek(Integer userId);

    /**
     * 查询某个用户的统计情况
     *
     * @param userId the user id
     * @return the statistics
     */
    public Statistics getStatistics(Integer userId);


}
