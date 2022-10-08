package com.zqy.blog_admin.system.service;

import com.zqy.blog_admin.system.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author zqy
 * @since 2022-09-28
 */
public interface UserService extends IService<User> {

    User findByUsername(String username);
}
