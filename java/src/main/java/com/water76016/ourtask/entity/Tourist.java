package com.water76016.ourtask.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: our-task
 * @description: 游客访问对象
 * @author: water76016
 * @create: 2020-09-24 16:45
 **/
@Data
public class Tourist  implements Serializable {
    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "登录密码")
    private String password;
}
