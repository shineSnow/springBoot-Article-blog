package com.zqy.blog_admin.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author zqy
 * @since 2022-09-29
 */
@Getter
@Setter
@TableName("t_permission")
@ApiModel(value = "Permission对象", description = "权限表")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("menu_title")
    private String menuTitle;

    @TableField("parent_id")
    private Long parentId;

    @TableField("menu_type")
    private Integer menuType;

    @TableField("menu_sort")
    private Integer menuSort;

    @TableField("menu_path")
    private String menuPath;

    @TableField("menu_icon")
    private String menuIcon;

    @TableField("component")
    private String component;

    @TableField("is_hidden")
    private Integer isHidden;

    @TableField("permission")
    private String permission;

    @TableField(exist = false)
    private List<Permission> children;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;


}
