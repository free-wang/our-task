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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    public List<Map<Object, Object>> list(Integer userId) {
        String key = redisDatabase + ":" + redisKeyCategory + ":" + "list" + ":" + userId;
        if (redisService.hasKey(key)){
            //先获取该用户下所有的清单id
            Set<Object> categoryIdSet = redisService.sMembers(key);
            redisService.hge
        }




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
        if (flag == false){
            return flag;
        }
        saveOrUpdateForRedis(entity);
        //把这个分类的id，添加到redis的用户分类id集合里面
        String key = redisDatabase + ":" + redisKeyCategory + ":" + entity.getUserId();
        redisService.sAdd(key, entity.getId());
        return flag;
    }

    /**
     * 用来对一个分类进行更新
     * */
    @Override
    public boolean updateById(Category entity) {
        boolean flag = super.updateById(entity);
        if (flag == false){
            return false;
        }
        saveOrUpdateForRedis(entity);
        return flag;
    }

    /**
     * 针对redis里面存储的category，进行添加或者更新
     * @param entity
     */
    public void saveOrUpdateForRedis(Category entity){
        String key = redisDatabase + ":" + redisKeyCategory + ":" + entity.getId();
        redisService.expire(key, expire);
        redisService.hSet(key, "id", entity.getId());
        redisService.hSet(key, "userId", entity.getUserId());
        redisService.hSet(key, "name", entity.getName());
        redisService.hSet(key, "createTime", entity.getCreateTime());
        redisService.hSet(key, "updateTime", entity.getUpdateTime());
        redisService.hSet(key, "taskCount", entity.getTaskCount());
    }

    @Override
    public boolean removeById(Integer id, Integer userId) {
        //如果该分类下，清单数目大于0，则该清单不能被删除
        String key = redisDatabase + ":" + redisKeyCategory + ":" + id;
        String userCategory = redisDatabase + ":" + redisKeyCategory + ":" + userId;
        if (redisService.hasKey(key)){
            int taskCount = (int)redisService.hGet(key, "taskCount");
            if (taskCount > 0){
                return false;
            }
        }
        Category category = super.getById(id);
        if (category.getTaskCount() <= 0){
            removeById(id);
            redisService.sRemove(userCategory, id);
            return true;
        }
        //发现这个分类清单数不为0，并且不在redis中，顺便存进去
        saveOrUpdateForRedis(category);
        redisService.sAdd(userCategory, id);

        return false;
    }
}
