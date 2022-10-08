package com.zqy.blog_admin.system.controller;

import com.zqy.blog_admin.system.entity.RolePermission;
import com.zqy.blog_admin.system.mapper.RolePermissionMapper;
import com.zqy.blog_admin.system.response.AjaxResult;
import com.zqy.blog_admin.system.vo.RolePermissionVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 角色权限表 前端控制器
 * </p>
 *
 * @author zqy
 * @since 2022-09-29
 */
@RestController
@RequestMapping("/system/rolePermission")
@Api(value = "/rolePermission",tags = "角色权限管理")
public class RolePermissionController {


    @Resource
    private RolePermissionMapper rolePermissionMapper;


    @PostMapping("add")
    @ApiOperation(value = "添加角色权限")
    public AjaxResult add (@RequestBody RolePermissionVo rolePermissionVo){

        RolePermission rolePermission = new RolePermission();

        BeanUtils.copyProperties(rolePermissionVo,rolePermission);

        int insert = rolePermissionMapper.insert(rolePermission);

        if (insert > 0) {
            return AjaxResult.success(rolePermission.getId());
        }

        return AjaxResult.error();
    }

}
