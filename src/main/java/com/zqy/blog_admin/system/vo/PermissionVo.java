package com.zqy.blog_admin.system.vo;


import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "权限实体")
public class PermissionVo implements Serializable {

    private static final long serialVersionUID = 3653370164107378627L;

    @ApiModelProperty(value = "菜单标题",required = true)
    private String menuTitle;

    @ApiModelProperty(value = "父节点id",required = true)
    private Long parentId;

    @ApiModelProperty(value = "菜单类型",required = true,notes = "0：顶层菜单；1：二级菜单；2：按钮")
    private Integer menuType;

    @ApiModelProperty(value = "菜单排序",required = true)
    private Integer menuSort;

    @ApiModelProperty(value = "菜单路由",required = true)
    private String menuPath;

    @ApiModelProperty(value = "菜单icon",required = true)
    private String menuIcon;

    @ApiModelProperty(value = "菜单所对应组件",required = true)
    private String component;

    @ApiModelProperty(value = "菜单是否隐藏",required = true)
    private Integer isHidden;

    @ApiModelProperty(value = "菜单权限",required = true)
    private String permission;
}
