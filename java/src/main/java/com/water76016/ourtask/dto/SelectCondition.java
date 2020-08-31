package com.water76016.ourtask.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 对清单进行查询的查询对象
 * */
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
