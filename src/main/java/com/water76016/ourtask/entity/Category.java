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
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 清单id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 清单名称
     */
    private String name;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否被删除
     */
    private Integer run;

    public Category(Integer userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public Category(Integer id, Integer run) {
        this.id = id;
        this.run = run;
    }

    public Category(Integer id, Integer userId, String name) {
        this.id = id;
        this.userId = userId;
        this.name = name;
    }
}
