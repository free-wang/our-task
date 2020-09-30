package com.water76016.ourtask.common;

/**
 * 封装API的错误码
 * Created by macro on 2019/4/19.
 */
public interface IErrorCode {
    /**
     * 得到状态码
     * @return:状态码
     */
    long getCode();

    /**
     * 获取返回消息
     * @return:message返回消息
     */
    String getMessage();
}
