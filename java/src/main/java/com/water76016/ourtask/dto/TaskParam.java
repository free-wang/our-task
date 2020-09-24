package com.water76016.ourtask.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @program: our-task
 * @description: task控制器的传参对象
 * @author: water76016
 * @create: 2020-09-24 16:45
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskParam {
    /**
     * 清单id
     * */
    Integer id;
    /**
     * 用户id
     * */
    Integer userId;
    /**
     * 分类id
     */
    Integer categoryId;
    /**
     * 清单名称
     */
    String name;
    /**
     * 所属标签列表
     */
    List<Integer> labelList;
    /**
     * 清单描述
     */
    String description;
}
