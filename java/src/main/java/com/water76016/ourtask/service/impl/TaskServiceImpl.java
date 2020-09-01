package com.water76016.ourtask.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.water76016.ourtask.dto.Statistics;
import com.water76016.ourtask.entity.Task;
import com.water76016.ourtask.mapper.TaskMapper;
import com.water76016.ourtask.service.TaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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
        taskQueryWrapper.eq("run", 1);
        Integer countTask = count(taskQueryWrapper);
        return countTask;
    }

    @Override
    public Integer countTaskByLabelId(Integer userId, Integer labelId) {
        QueryWrapper<Task> taskQueryWrapper = new QueryWrapper<>();
        taskQueryWrapper.eq("run", 1);
        taskQueryWrapper.eq("user_id", userId);
        taskQueryWrapper.eq("label_id", labelId);
        Integer countTask = count(taskQueryWrapper);
        return countTask;
    }

    @Override
    public List<TreeMap<String, String>> countTaskForDay(Integer userId) {
        List<TreeMap<String, String>> result = new ArrayList<>();
        //获取当前时间
        String localDate = LocalDate.now().toString();
        Date date = DateUtil.parse(localDate);
        for (int i = 6; i >= 0; i--){
            QueryWrapper<Task> taskQueryWrapper = new QueryWrapper<>();
            taskQueryWrapper.eq("user_id", userId);
            taskQueryWrapper.eq("run", 0);
            DateTime currentDay = DateUtil.offsetDay(date, -i);
            //一天的开始
            Date beginOfDay = DateUtil.beginOfDay(currentDay);
            //一天的结束
            Date endOfDay = DateUtil.endOfDay(currentDay);
            taskQueryWrapper.between("update_time", beginOfDay, endOfDay);
            taskQueryWrapper.ge("update_time", beginOfDay);
            Integer count = count(taskQueryWrapper);
            String format = DateUtil.format(currentDay, "yyyy-MM-dd");
            if (i == 0){
                format = "今天";
            }
            TreeMap<String, String> treeMap = new TreeMap<>();
            treeMap.put("date", format);
            treeMap.put("day", String.valueOf(count));
            result.add(treeMap);
        }
        return result;
    }

    @Override
    public List<TreeMap<String, String>> countTaskForWeek(Integer userId) {
        List<TreeMap<String, String>> result = new ArrayList<>();
        //获取当前时间
        String localDate = LocalDate.now().toString();
        Date date = DateUtil.parse(localDate);
        Date beginWeek = DateUtil.beginOfWeek(date);
        for (int i = 6; i >= 0; i--){
            QueryWrapper<Task> taskQueryWrapper = new QueryWrapper<>();
            taskQueryWrapper.eq("user_id", userId);
            taskQueryWrapper.eq("run", 0);
            //一周的开始
            Date beginOfWeek = DateUtil.offsetWeek(beginWeek, -i);
            //一周的结束
            Date endOfWeek = DateUtil.offsetWeek(beginWeek, -i + 1);
            taskQueryWrapper.ge("update_time", beginOfWeek);
            taskQueryWrapper.lt("update_time", endOfWeek);
            Integer count = count(taskQueryWrapper);
            String format = DateUtil.format(beginOfWeek, "yyyy-MM-dd");
            if (i == 0){
                format = "本周";
            }
            TreeMap<String, String> treeMap = new TreeMap<>();
            treeMap.put("date", format);
            treeMap.put("week", String.valueOf(count));
            result.add(treeMap);
        }
        return result;
    }

    @Override
    public Statistics getStatistics(Integer userId) {
        QueryWrapper<Task> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("run", 0);
        //统计已完成清单
        Integer totalFinished = count(queryWrapper);
        //统计待完成清单
        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("run", 1);
        Integer unFinished = count(queryWrapper);
        //统计上周完成清单
        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("run", 0);
        DateTime lastWeek = DateUtil.lastWeek();
        queryWrapper.ge("update_time", lastWeek);
        Integer weekFinished = count(queryWrapper);
        //统计上个月完成清单
        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("run", 0);
        DateTime lastMonth = DateUtil.lastMonth();
        queryWrapper.ge("update_time", lastMonth);
        Integer monthFinished = count(queryWrapper);

        return new Statistics(totalFinished, unFinished, weekFinished, monthFinished);
    }

}
