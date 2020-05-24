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
    void addCategory(String name);

    /**
     * 根据id删除一个清单分类
     * */
    void deleteCategoryById(int id);

    /**
     * 对清单分类的名称进行修改
     * */
    void updateCategory(Category category);

    /**
     * 查询所有的分类
     * */
    List<Category> getCategoryList();

    /**
     * 查询当前分类下的信息，也就是返回当前分类的对象
     * */
    Category getCategoryById(Integer categoryId);

}

