package com.zqy.blog_admin.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel(value = "用户注册参数模型",description = "用户注册参数模型")
public class UserVo  implements Serializable {

    private static final long serialVersionUID = 5876765466923926898L;

    @ApiModelProperty(value = "用户名",required = true)
    private String userName;

    @ApiModelProperty(value = "密码",required = true)
    private String password;

    @ApiModelProperty(value = "邮箱",required = true)
    private String email;

    @ApiModelProperty(value = "性别",required = true)
    private Integer sex;
}
