package com.water76016.ourtask.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统计用户的使用情况
 * */
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
