package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @RequestMapping("addCategory")
    public String addCategory(String name, Integer categoryId){
        categoryMapper.addCategory(name);
        return "redirect:/index?categoryId=" + categoryId;
    }

    /**
     * 根据id删除一个清单分类
     * */
    @RequestMapping("deleteCategoryById")
    String deleteCategoryById(Integer id){
        categoryMapper.deleteCategoryById(id);
        //删除一个分类之后，该分类下所有的清单都将消失
        taskMapper.deleteTaskByCategoryId(id);
        return "redirect:/index";
    }
    /**h
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

    /**
     * 根据categoryId查询当前分类下的所有信息，也就是返回当前的category对象
     * */
    @GetMapping("getCategoryById")
    Category getCategoryById(Integer categoryId){
        return categoryMapper.getCategoryById(categoryId);
    }


}
