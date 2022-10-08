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
@ApiModel(value = "角色实体")
public class RoleVo implements Serializable {

    private static final long serialVersionUID = -3233948791247255778L;

    @ApiModelProperty(value = "角色名称",required = true)
    private String roleName;

    @ApiModelProperty(value = "角色别名建议英文缩写",required = true)
    private String roleCode;

    @ApiModelProperty(value = "角色说明",required = true)
    private String roleMark;
}
