package com.zqy.blog_admin.system.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zqy.blog_admin.system.entity.RolePermission;
import com.zqy.blog_admin.system.mapper.RolePermissionMapper;
import com.zqy.blog_admin.system.service.RolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色权限表 服务实现类
 * </p>
 *
 * @author zqy
 * @since 2022-09-29
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

    @Override
    public List<RolePermission> findPermsByRoleId(long roleId) {

        QueryWrapper<RolePermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("rid",roleId);
        List<RolePermission> list = this.baseMapper.selectList(queryWrapper);
        return list;
    }
}
