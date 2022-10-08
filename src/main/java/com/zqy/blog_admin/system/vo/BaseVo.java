package com.zqy.blog_admin.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "基础vo模型",description = "分页")
public class BaseVo implements Serializable {

    private static final long serialVersionUID = 9038137729825684645L;
    // 当前页
    @ApiModelProperty(value = "当前页")
    private int current;
    //分页数量
    @ApiModelProperty(value = "分页数量")
    private int size;
}
