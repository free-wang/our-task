package com.water76016.ourtask.service;

import com.water76016.ourtask.common.RestResult;
import com.water76016.ourtask.entity.Tourist;

import javax.servlet.http.HttpServletResponse;


/**
 * 针对游客的Service
 * */
public interface TouristService {
    /**
     * 游客进行注册操作
     * */
    RestResult register(Tourist tourist);
    /**
     * 游客进行登录操作
     * */
    RestResult login(Tourist tourist, HttpServletResponse response);
}
