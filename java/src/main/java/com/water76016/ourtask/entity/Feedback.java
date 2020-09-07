package com.water76016.ourtask.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author github:water76016
 * @since 2020-09-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Feedback implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 反馈id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 所属用户id
     */
    private Integer userId;

    /**
     * 反馈标题
     */
    private String title;

    /**
     * 反馈描述
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

    public Feedback(Integer userId, String title, String description) {
        this.userId = userId;
        this.title = title;
        this.description = description;
    }
}
