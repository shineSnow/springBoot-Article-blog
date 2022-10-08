package com.zqy.blog_admin.system.service;

import com.zqy.blog_admin.system.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author zqy
 * @since 2022-09-29
 */
public interface RoleService extends IService<Role> {

        Role findById(Long roleId);

}
