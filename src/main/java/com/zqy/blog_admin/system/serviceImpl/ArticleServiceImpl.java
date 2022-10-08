package com.zqy.blog_admin.system.serviceImpl;

import com.zqy.blog_admin.system.entity.Article;
import com.zqy.blog_admin.system.mapper.ArticleMapper;
import com.zqy.blog_admin.system.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章表 服务实现类
 * </p>
 *
 * @author zqy
 * @since 2022-09-28
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

}
