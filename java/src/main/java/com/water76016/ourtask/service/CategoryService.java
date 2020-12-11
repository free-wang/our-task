package com.water76016.ourtask.service;

import com.water76016.ourtask.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author github:water76016
 * @since 2020-07-21
 */
public interface CategoryService extends IService<Category> {
    boolean removeById(Integer id, Integer userId);

    List<Map<Object, Object>> list(Integer userId);
}
