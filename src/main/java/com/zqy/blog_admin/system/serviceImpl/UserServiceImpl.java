package com.zqy.blog_admin.system.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zqy.blog_admin.system.entity.User;
import com.zqy.blog_admin.system.mapper.UserMapper;
import com.zqy.blog_admin.system.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author zqy
 * @since 2022-09-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User findByUsername(String username) {

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username",username);
        return this.getOne(userQueryWrapper);
    }
}
