package com.zqy.blog_admin.system.controller;

import com.zqy.blog_admin.system.entity.Permission;
import com.zqy.blog_admin.system.mapper.PermissionMapper;
import com.zqy.blog_admin.system.response.AjaxResult;
import com.zqy.blog_admin.system.vo.PermissionVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author zqy
 * @since 2022-09-29
 */
@RestController
@RequestMapping("/system/permission")
@Api(value = "/permission",tags = "权限管理")
public class PermissionController {

    @Resource
    private PermissionMapper permissionMapper;

    @PostMapping("create")
    @ApiOperation(value = "创建权限")
    public AjaxResult create(@RequestBody PermissionVo permissionVo) {

        Permission permission = new Permission();

        BeanUtils.copyProperties(permissionVo,permission);

        int insert = permissionMapper.insert(permission);

        if (insert >0) {
            return AjaxResult.success(permission.getId());

        }
        return AjaxResult.error();
    }

}
