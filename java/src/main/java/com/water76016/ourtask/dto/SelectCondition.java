package com.water76016.ourtask.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @program: our-task
 * @description: 对清单列表进行查询的查询对象
 * @author: water76016
 * @create: 2020-09-24 16:45
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectCondition {
    /**
     * 查询名称
     * */
    String name;
    /**
     * 查询分类id
     * */
    Integer categoryId;
    /**
     * 查询标签id列表
     * */
    List<Integer> labelIdList;
}
