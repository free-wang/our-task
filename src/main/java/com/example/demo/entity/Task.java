package com.example.demo.entity;

import java.util.Date;

public class Task {
    private Integer id;
    private Integer categoryId;
    private String name;
    private String description;
    private Date createTime;
    private Date updateTime;
    private Integer run;

    public Integer getId() {
        return id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
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

    public Integer getRun() {
        return run;
    }

    public void setIsRun(Integer isRun) {
        this.run = run;
    }

    public Task(Integer id, Integer categoryId, String name, String description, Integer run) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.run = run;
    }

    /**
     * 如果不加这个无参构造器，可能会提示没有找到构造器的错误
     * */
    public Task() {
    }

    public Task(Integer categoryId, Integer run) {
        this.categoryId = categoryId;
        this.run = run;
    }

    public Task(Integer id, Integer categoryId, String name, String description, Date createTime, Date updateTime, Integer run) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.run = run;
    }
}
