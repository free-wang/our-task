package com.example.demo.mapper;

import com.example.demo.entity.Category;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    /**
     * 添加一个清单分类
     * */
    void addCategory(Category category);

    /**
     * 删除一个分类，删除之后，所包含的清单也会一起被删除（都是逻辑删除）
     * */
    void deleteCategoryById(Integer id);

    /**
     * 对分类进行更新
     * */
    void updateCategory(Category category);

    /**
     * 通过用户Id查询当前用户所有的分类
     * */
    List<Category> getCategoryListByUserId(Integer userId);

    /**
     * 查询当前分类下的信息，也就是返回当前分类的对象
     * */
    Category getCategoryById(Integer categoryId);

}

