package com.zqy.blog_admin.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel(value = "用户角色关联vo")
public class UserRoleVo implements Serializable {

    private static final long serialVersionUID = 2416694774394288971L;

    @ApiModelProperty(value = "用户id",required = true)
    private Long uid;

    @ApiModelProperty(value = "角色id",required = true)
    private Long rid;
}
