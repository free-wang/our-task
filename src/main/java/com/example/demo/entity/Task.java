package com.example.demo.entity;

import java.util.Date;

public class Task {
    private int id;
    private int category_id;
    private String name;
    private String description;
    private Date createTime;
    private Date updateTime;
    private int run;

    public int getId() {
        return id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getRun() {
        return run;
    }

    public void setIsRun(int isRun) {
        this.run = run;
    }
}
