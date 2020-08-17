package com.water76016.ourtask.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
