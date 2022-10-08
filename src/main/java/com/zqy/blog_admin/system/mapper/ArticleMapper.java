package com.zqy.blog_admin.system.mapper;

import com.zqy.blog_admin.system.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 文章表 Mapper 接口
 * </p>
 *
 * @author zqy
 * @since 2022-09-28
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

}
