package com.water76016.ourtask.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;

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
public class User implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 电话号码
     */
    private String telephone;

    /**
     * 密码
     */
    private String password;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像
     */
    private String headPortrait;

    /**
     * 权限
     */
    private String roles;

    /**
     * 是否被冻结
     */
    private Integer status;


    /**
     * Mybatis plus @TableName实体中添加非数据库字段报错，如增加请在字段上加注解 @TableField(exist = false)
     * */
    @TableField(exist = false)
    private List<GrantedAuthority> authorities;

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * 账户是否未过期，过期无法验证
     * */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 指定用户是否解锁，锁定的用户无法进行身份认证
     * */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 指示是否已过期的用户的凭据（密码），过期的凭据防伪认证
     * */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否可用
     * */
    @Override
    public boolean isEnabled() {
        return true;
    }

    public User(Integer id, String username, String telephone, String email) {
        this.id = id;
        this.username = username;
        this.telephone = telephone;
        this.password = password;
        this.email = email;
    }
}
