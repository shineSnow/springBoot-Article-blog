package com.zqy.blog_admin.system.serviceImpl;

import com.zqy.blog_admin.system.entity.Role;
import com.zqy.blog_admin.system.mapper.RoleMapper;
import com.zqy.blog_admin.system.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author zqy
 * @since 2022-09-29
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public Role findById(Long roleId) {
        return this.getById(roleId);
    }
}
