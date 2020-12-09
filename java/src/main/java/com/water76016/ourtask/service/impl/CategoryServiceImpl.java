package com.water76016.ourtask.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
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
    private String redisDatabase;
    @Value("${redis.key.category}")
    private String redisKeyCategory;
    @Value("${redis.expire.common}")
    private long expire;
    @Autowired
    RedisService redisService;

    /**
     * 用来获取当前用户下所有的分类
     * */
    @Override
    public List<Category> list(Wrapper<Category> queryWrapper) {
        Integer userId = queryWrapper.getEntity().getUserId();
        String key = redisDatabase + ":" + redisKeyCategory + ":" + "list" + ":" + userId;
        if (redisService.hasKey(key)){
            Long size = redisService.lSize(key);
            List<Object> save = redisService.lRange(key, 0, size);
            List<Category> categoryList = new ArrayList<>();
            for(Object object : save){
                //这是把一个Json的字符串，转换为一个对象
                Category category = JSONUtil.toBean(object.toString(), Category.class);
                categoryList.add(category);
            }
            return categoryList;
        }
        List<Category> categoryList = super.list(queryWrapper);
        for(Category category : categoryList){
            String save = JSONUtil.toJsonStr(category);
            redisService.lPush(key, save);
            //设置键过期时间
            redisService.expire(key, expire);
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
            String key = redisDatabase + ":" + redisKeyCategory + ":" + "list" + ":" + entity.getUserId();
            String save = JSONUtil.toJsonStr(entity);
            redisService.lPush(key, save);
            redisService.expire(key, expire);
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
            String key = redisDatabase + ":" + redisKeyCategory + ":" + "list" + ":" + userId;
            //更新的时候直接删除这个键，等后面查询所有自动更新那么redis没有键，会自动跑到mysql中去查询，
            // 然后自动添加到redis
            redisService.del(key);
        }
        return flag;
    }
}
