package com.zqy.blog_admin.system.service;

import com.zqy.blog_admin.system.entity.RolePermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色权限表 服务类
 * </p>
 *
 * @author zqy
 * @since 2022-09-29
 */
public interface RolePermissionService extends IService<RolePermission> {

    List<RolePermission> findPermsByRoleId(long roleId);
}
