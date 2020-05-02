package com.example.demo.mapper;

import com.example.demo.entity.Category;

public interface CategoryMapper {
    /**
     * 添加一个清单分类
     * */
    void addCategory(Category category);

    /**
     * 根据id删除一个清单分类
     * */
    void deleteCategoryById(int id);

    /**
     * 对清单分类的名称进行修改
     * */
    void updateCategory(Category category);

}

