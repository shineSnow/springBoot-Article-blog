package com.zqy.blog_admin.system.controller;

import com.zqy.blog_admin.system.entity.UserRole;
import com.zqy.blog_admin.system.mapper.UserRoleMapper;
import com.zqy.blog_admin.system.response.AjaxResult;
import com.zqy.blog_admin.system.vo.UserRoleVo;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 用户角色表 前端控制器
 * </p>
 *
 * @author zqy
 * @since 2022-09-29
 */
@RestController
@RequestMapping("/system/userRole")
@Api(value = "/userRole",tags = "用户角色管理")
public class UserRoleController {
    @Resource
    private UserRoleMapper userRoleMapper;

    @PostMapping("add")
    public AjaxResult addRole(@RequestBody UserRoleVo userRoleVo){
        UserRole userRole = new UserRole();

        BeanUtils.copyProperties(userRoleVo,userRole);

        int insert = userRoleMapper.insert(userRole);

        if (insert == 0) {
            return AjaxResult.error();
        }

        return AjaxResult.success(userRole.getId());
    }

}
