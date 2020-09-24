package com.water76016.ourtask.entity;

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
    private String username;

    private String password;
}
