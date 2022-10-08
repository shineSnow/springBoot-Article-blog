package com.zqy.blog_admin.system.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel(value = "角色权限关联vo")
public class RolePermissionVo implements Serializable {


    private static final long serialVersionUID = 2946646412746973914L;


    @ApiModelProperty(value = "角色id",required = true)
    private Long rid;


    @ApiModelProperty(value = "权限id",required = true)
    private Long permId;
}
