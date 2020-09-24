package com.water76016.ourtask.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: our-task
 * @description: 针对分类控制器传参所创建的Param类
 * @author: water76016
 * @create: 2020-09-24 16:45
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryParam {
    /**
     * 分类id
     * */
    Integer id;
    /**
     * 分类名称
     * */
    String name;
    /**
     * 所属清单总数
     * */
    Integer count;
}
