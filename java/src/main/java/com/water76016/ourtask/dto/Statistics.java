package com.water76016.ourtask.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: our-task
 * @description: 统计用户对于系统的使用情况查询的传输对象
 * @author: water76016
 * @create: 2020-09-24 16:45
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Statistics {
    /**
     * 已完成清单
     * */
    Integer totalFinished;
    /**
     * 待完成清单
     * */
    Integer unFinished;
    /**
     * 上周完成清单
     * */
    Integer weekFinished;
    /**
     * 上个月完成清单
     * */
    Integer monthFinished;
}
