package com.water76016.ourtask.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.water76016.ourtask.common.RestResult;
import com.water76016.ourtask.dto.CategoryParam;
import com.water76016.ourtask.entity.Category;
import com.water76016.ourtask.entity.Task;
import com.water76016.ourtask.service.CategoryService;
import com.water76016.ourtask.service.RedisService;
import com.water76016.ourtask.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author github:water76016
 * @since 2020-07-21
 */
@Api(value = "分类控制", tags = {"分类操作"})
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    TaskService taskService;

    @Autowired
    CategoryService categoryService;

    @ApiOperation("添加一个新的分类")
    @PostMapping("add")
    public RestResult add(@RequestBody @ApiParam("新增分类对象") Category category){
        boolean flag = categoryService.save(category);
        if (flag){
            return RestResult.success(category);
        }
        return RestResult.error();
    }

    @ApiOperation("逻辑删除一个分类")
    @GetMapping("delete/{id}")
    public RestResult delete(@PathVariable("id") Integer id){
        categoryService.removeById(id);
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
        //下面是根据分类id,找到所属的清单总数
        List<CategoryParam> categoryParamList = new ArrayList<>();
        for(Category cate : categoryList){
            Integer categoryId = cate.getId();
            QueryWrapper<Task> taskQueryWrapper = new QueryWrapper<>();
            taskQueryWrapper.eq("user_id", userId);
            taskQueryWrapper.eq("category_id", categoryId);
            Integer countTask = taskService.count(taskQueryWrapper);
            CategoryParam categoryParam = new CategoryParam(cate.getId(), cate.getName(), countTask);
            categoryParamList.add(categoryParam);
        }

        return RestResult.success(categoryParamList);
    }

    @ApiOperation("根据分类id查询分类对象")
    @GetMapping("get/{categoryId}")
    public Category getCategoryById(@PathVariable("categoryId") String categoryId){
        return categoryService.getById(Integer.valueOf(categoryId));
    }

    @ApiOperation("查询当前用户，当前页的分类列表")
    @GetMapping("/getPageList/{userId}/{pageCurrent}/{pageSize}")
    public RestResult getPageList(@PathVariable("userId") Integer userId,
                                  @PathVariable("pageCurrent") Integer pageCurrent,
                                  @PathVariable("pageSize") Integer pageSize){
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("run", 1);
        Page<Category> page = new Page<>();
        page.setCurrent(pageCurrent);
        page.setSize(pageSize);
        IPage<Category> categoryPage = categoryService.page(page, queryWrapper);

        for (Category category : categoryPage.getRecords()){
            Integer categoryId = category.getId();
            Integer countTask = taskService.countTask(userId, categoryId);
            category.setTaskCount(countTask);
        }
        return RestResult.success("得到当前分页清单成功", categoryPage);
    }

}
