package com.example.demo.entity;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    //用户id
    private Integer id;
    //用户电话号码
    private String telephone;
    //用户名称
    @NonNull
    private String name;
    //用户账户密码
    @NonNull
    private String password;
    //用户创建时间
    private Date createTime;
    //用户更新时间
    private Date updateTime;
    //用户邮箱
    private String email;
    //用户头像
    private String headPortrait;
    //用户是否被冻结
    private Integer freeze;

    public User(String telephone, @NonNull String name, @NonNull String password) {
        this.telephone = telephone;
        this.name = name;
        this.password = password;
    }

    public User(String telephone, @NonNull String name, @NonNull String password, String email, String headPortrait, Integer freeze) {
        this.telephone = telephone;
        this.name = name;
        this.password = password;
        this.email = email;
        this.headPortrait = headPortrait;
        this.freeze = freeze;
    }
}
