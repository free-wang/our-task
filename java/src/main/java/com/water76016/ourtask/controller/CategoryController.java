package com.water76016.ourtask.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.water76016.ourtask.common.RestResult;
import com.water76016.ourtask.entity.Category;
import com.water76016.ourtask.service.CategoryService;
import com.water76016.ourtask.service.RedisService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author github:water76016
 * @since 2020-07-21
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @ApiOperation("添加一个新的分类")
    @PostMapping("add/{userId}")
    public RestResult add(@PathVariable("userId") Integer userId, @RequestBody Category category){
        boolean flag = categoryService.save(category);
        if (flag){
            return RestResult.success(category);
        }
        return RestResult.error();
    }

    @ApiOperation("逻辑删除一个分类")
    @GetMapping("delete/{id}")
    public RestResult delete(@PathVariable("id") Integer id){
        Category category = new Category(id, 0);
        categoryService.updateById(category);
        return RestResult.success();
    }

    @ApiOperation("修改分类的名称")
    @PostMapping("update/{id}")
    public RestResult update(@PathVariable("id") Integer id, @RequestBody Category category){
        categoryService.updateById(category);
        return RestResult.success();
    }

    @ApiOperation("查询当前用户的所有分类")
    @GetMapping("listAll/{userId}")
    public RestResult listAll(@PathVariable("userId") Integer userId){
        Category category = new Category(userId);
        category.setRun(1);
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(category);
        List<Category> categoryList = categoryService.list(queryWrapper);
        return RestResult.success(categoryList);
    }
}
