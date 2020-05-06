package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("our-task")
public class CategoryController {
    @Autowired(required = false)
    private CategoryMapper categoryMapper;

    /**
     * 添加一个清单分类
     * */
    @PostMapping("addCategory")
    public void addCategory(String name){
        categoryMapper.addCategory(name);
    }

    /**
     * 根据id删除一个清单分类
     * */
    @PostMapping("deleteCategoryById")
    void deleteCategoryById(Integer id){
        categoryMapper.deleteCategoryById(id);
    }
    /**
     * 对清单分类的名称进行修改
     * */
    @PostMapping("updateCategory")
    void updateCategory(Integer id, String name){
        Category category = new Category(id, name);
        categoryMapper.updateCategory(category);
    }
    /**
     * 查询所有的分类名称
     * */
    @GetMapping("getCategoryList")
    List<Category> getCategoryList(){
        return categoryMapper.getCategoryList();
    }


}
