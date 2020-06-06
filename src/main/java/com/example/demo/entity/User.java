package com.example.demo.entity;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    //用户id
    private Integer id;
    //用户名称
    @NonNull
    private String name;
    //用户账号
    @NonNull
    private String accountNumber;
    //用户账户密码
    @NonNull
    private String password;
    //用户创建时间
    private Date createTime;
    //用户更新时间
    private Date updateTime;
    //用户电话号码
    private String telephone;
    //用户邮箱
    private String email;
    //用户头像
    private String headPortrait;
    //用户是否被冻结
    private Integer freeze;
}
