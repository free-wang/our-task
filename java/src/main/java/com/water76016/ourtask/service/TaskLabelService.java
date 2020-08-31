package com.water76016.ourtask.service;

import com.water76016.ourtask.entity.TaskLabel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author github:water76016
 * @since 2020-08-11
 */
public interface TaskLabelService extends IService<TaskLabel> {

    /**
     * 根据清单id，查询当前清单所属的标签id列表
     */
    public List<Integer> getLableListByTaskId(Integer taskId);

    /**
     * 根据标签id列表，查询对应的id列表
     * */
    public List<Integer> getTaskIdListByLabelIdList(List<Integer> labelIdList);

}
