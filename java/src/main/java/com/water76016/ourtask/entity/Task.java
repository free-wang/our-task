package com.water76016.ourtask.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author github:water76016
 * @since 2020-07-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 任务id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 所属清单id
     */
    private Integer categoryId;

    /**
     * 任务名
     */
    private String name;

    /**
     * 任务描述
     */
    private String description;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否完成
     */
    private Integer run;

    public Task(Integer userId, Integer categoryId, String name) {
        this.userId = userId;
        this.categoryId = categoryId;
        this.name = name;
    }

    public Task(Integer id, Integer run) {
        this.id = id;
        this.run = run;
    }

    public Task(Integer id, Integer categoryId, String name, String description) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
    }
}
