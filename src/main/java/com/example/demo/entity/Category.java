package com.example.demo.entity;

import lombok.*;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    //分类id
    @NonNull
    private Integer id;
    //所属用户id
    @NonNull
    private Integer userId;
    //分类名称
    @NonNull
    private String name;
    //分类创建时间
    private Date createTime;
    //分类修改时间
    private Date updateTime;
    //分类是否还在使用
    private Integer run;

    public Category(@NonNull Integer userId, @NonNull String name) {
        this.userId = userId;
        this.name = name;
    }

    public Category(@NonNull Integer id, @NonNull Integer userId, @NonNull String name) {
        this.id = id;
        this.userId = userId;
        this.name = name;
    }
}
