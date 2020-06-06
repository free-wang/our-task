package com.example.demo.entity;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    //清单id
    @NonNull
    private Integer id;
    //用户id
    @NonNull
    private Integer userId;
    //分类id
    @NonNull
    private Integer categoryId;
    //清单名称
    @NonNull
    private String name;
    //清单描述
    private String description;
    //清单创建时间
    private Date createTime;
    //清单更新时间
    private Date updateTime;
    //清单是否还在使用
    private Integer run;

    public Task(@NonNull Integer userId, @NonNull Integer categoryId, Integer run) {
        this.userId = userId;
        this.categoryId = categoryId;
        this.run = run;
    }

    public Task(@NonNull Integer userId, @NonNull Integer categoryId, @NonNull String name, String description) {
        this.userId = userId;
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
    }

    public Task(@NonNull Integer id, @NonNull Integer userId, @NonNull Integer categoryId, @NonNull String name, String description, Integer run) {
        this.id = id;
        this.userId = userId;
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.run = run;
    }
}
