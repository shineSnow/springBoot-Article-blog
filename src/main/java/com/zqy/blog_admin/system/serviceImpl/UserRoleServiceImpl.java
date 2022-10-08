package com.zqy.blog_admin.system.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zqy.blog_admin.system.entity.UserRole;
import com.zqy.blog_admin.system.mapper.UserRoleMapper;
import com.zqy.blog_admin.system.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author zqy
 * @since 2022-09-29
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Override
    public List<UserRole> listByUserId(long userId) {

        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid",userId);
        List<UserRole> userRoleList = this.list(queryWrapper);
        return userRoleList;
    }
}
