package com.zqy.blog_admin.system.service;

import com.zqy.blog_admin.system.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户角色表 服务类
 * </p>
 *
 * @author zqy
 * @since 2022-09-29
 */
public interface UserRoleService extends IService<UserRole> {
    /**
     * 根据用户id获取所有角色
     * @param userId
     * @return
     */
    List<UserRole> listByUserId(long userId);
}
