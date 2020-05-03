package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TaskMapper {
    /**
     * 添加一个新的清单
     * */
    void addTask(String name);
    /**
     * 删除一个清单
     * */
}
