package com.zqy.blog_admin.system.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zqy.blog_admin.system.entity.Article;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(value = "Article对象", description = "文章表")
public class ArticleVo extends BaseVo {
    @ApiModelProperty(value = "博客id",required=false)
    private Long id;

    @ApiModelProperty(value = "博客标题",required=false)
    private String title;

    @ApiModelProperty(value = "博客作者",required=false)
    private String author;

    @ApiModelProperty(value = "博客正文",required=false)
    private String content;

    @ApiModelProperty(value = "博客标签",required=false)
    private String tag;

    @ApiModelProperty(value = "博客简介",required=false)
    private String summary;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;
}
