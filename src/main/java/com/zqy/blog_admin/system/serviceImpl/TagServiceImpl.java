package com.zqy.blog_admin.system.serviceImpl;

import com.zqy.blog_admin.system.entity.Tag;
import com.zqy.blog_admin.system.mapper.TagMapper;
import com.zqy.blog_admin.system.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zqy
 * @since 2022-09-28
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

}
