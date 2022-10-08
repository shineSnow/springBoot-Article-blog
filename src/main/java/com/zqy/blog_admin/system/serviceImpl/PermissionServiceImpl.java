package com.zqy.blog_admin.system.serviceImpl;

import com.zqy.blog_admin.system.entity.Permission;
import com.zqy.blog_admin.system.mapper.PermissionMapper;
import com.zqy.blog_admin.system.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author zqy
 * @since 2022-09-29
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

}
