package com.zqy.blog_admin.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zqy.blog_admin.system.entity.Article;
import com.zqy.blog_admin.system.mapper.ArticleMapper;
import com.zqy.blog_admin.system.vo.ArticleVo;
import com.zqy.blog_admin.system.vo.BaseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章表 前端控制器
 * </p>
 *
 * @author zqy
 * @since 2022-09-28
 */
@RestController
@RequestMapping("/system/article")
@Api(value = "/article", tags = "博文管理")
public class ArticleController {


    @Resource
    private ArticleMapper articleMapper;

    @GetMapping("list")
    @ApiOperation(value = "分页查询全部文章")
    @PreAuthorize("hasRole('manager') AND hasAuthority('system:article:list')")
    public Map<String,Object> getList(ArticleVo articleParams){

        int current = articleParams.getCurrent();
        int size = articleParams.getSize();

        Page<Article> page = new Page<>(current, size);

        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        Page<Article> selectPage = articleMapper.selectPage(page, queryWrapper);

        HashMap<String, Object> map = new HashMap<>();
        map.put("code",200);
        map.put("success",true);
        map.put("list",selectPage);

        return map;
    }

    @GetMapping("queryById")
    @ApiOperation(value = "根据id查询某个文章")
    public Map<String,Object> queryById(int id){

        Article article = articleMapper.selectById(id);

        HashMap<String, Object> map = new HashMap<>();
        map.put("code",200);
        map.put("success",true);
        map.put("data",article);

        return map;
    }

}
