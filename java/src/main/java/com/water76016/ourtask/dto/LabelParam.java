package com.water76016.ourtask.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: our-task
 * @description: 针对label控制器所创建的传参实体类
 * @author: water76016
 * @create: 2020-09-24 16:45
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LabelParam {
    /**
     * 标签id
     * */
    Integer id;
    /**
     * 标签名称
     * */
    String name;
    /**
     * 所含清单总数
     * */
    Integer count;

}
