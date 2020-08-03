package com.water76016.ourtask.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.water76016.ourtask.entity.Category;
import com.water76016.ourtask.mapper.CategoryMapper;
import com.water76016.ourtask.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.water76016.ourtask.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author github:water76016
 * @since 2020-07-21
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Value("${redis.key.category}")
    private String REDIS_KEY_CATEGORY;
    @Value("${redis.expire.common}")
    private String REDIS_EXPIRE_COMMON;
    @Autowired
    RedisService redisService;

    /**
     * 用来获取当前用户下所有的分类
     * */
    @Override
    public List<Category> list(Wrapper<Category> queryWrapper) {
        Integer userId = queryWrapper.getEntity().getUserId();
        String key = REDIS_DATABASE + ":" + REDIS_KEY_CATEGORY + ":" + "list" + ":" + userId;
        if (redisService.hasKey(key)){
            Long size = redisService.lSize(key);
            List<Object> save = redisService.lRange(key, 0, size);
            List<Category> categoryList = new ArrayList<>();
            for(Object object : save){
                Category category = (Category)object;
                categoryList.add((Category)category);
            }
            return categoryList;
        }
        List<Category> categoryList = super.list(queryWrapper);
        List<Category> result = new ArrayList<>();
        for(Category category : categoryList){
            redisService.lPush(key, category);
        }

        return categoryList;
    }

    /**
     * 用来添加一个新的分类
     * */
    @Override
    public boolean save(Category entity) {
        //添加一个实体，会自动把数据库中的id传值回来
        boolean flag = super.save(entity);
        if (flag){
            String key = REDIS_DATABASE + ":" + REDIS_KEY_CATEGORY + ":" + "list" + ":" + entity.getUserId();
            redisService.lPush(key, entity);
        }
        return flag;
    }

    /**
     * 用来对一个分类进行更新
     * */
    @Override
    public boolean updateById(Category entity) {
        boolean flag = super.updateById(entity);
        if (flag){
            Integer userId = this.getById(entity.getId()).getUserId();
            String key = REDIS_DATABASE + ":" + REDIS_KEY_CATEGORY + ":" + "list" + ":" + userId;
            //更新的时候直接删除这个键，等后面查询所有自动更新那么redis没有键，会自动跑到mysql中去查询，
            // 然后自动添加到redis
            redisService.del(key);
        }
        return flag;
    }
}
