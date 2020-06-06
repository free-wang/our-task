package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("our-task")
public class CategoryController {
    @Autowired(required = false)
    private CategoryMapper categoryMapper;

    @Autowired(required = false)
    private TaskMapper taskMapper;

    /**
     * 添加一个清单分类
     * */
    @PostMapping("addCategory")
    public void addCategory(Integer userId, String name){
        Category category = new Category(userId, name);
        categoryMapper.addCategory(category);
//        return "redirect:/index?categoryId=" + category.getId();
    }

    /**
     * 根据id删除一个清单分类
     * */
    @GetMapping("deleteCategory")
    void deleteCategoryById(Integer id){
        categoryMapper.deleteCategory(id);
//        删除一个分类之后，该分类下所有的清单都将消失
//        taskMapper.deleteTaskByCategoryId(id);
//        return "redirect:/index";
    }
    /**h
     * 对清单分类的名称进行修改
     * */
    @PostMapping("updateCategory")
    void updateCategory(Integer id, Integer userId, String name){

        Category category = new Category(id, userId, name);
        categoryMapper.updateCategory(category);
    }
    /**
     * 查询所有的分类名称
     * */
    @GetMapping("getCategoryList")
    List<Category> getCategoryListByUserId(Integer userId){
        return categoryMapper.getCategoryListByUserId(userId);
    }

    /**
     * 根据categoryId查询当前分类下的所有信息，也就是返回当前的category对象
     * */
    @GetMapping("getCategoryById")
    Category getCategoryById(Integer categoryId){
        return categoryMapper.getCategoryById(categoryId);
    }

}
