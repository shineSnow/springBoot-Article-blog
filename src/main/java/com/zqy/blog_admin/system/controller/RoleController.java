package com.zqy.blog_admin.system.controller;

import com.zqy.blog_admin.system.entity.Role;
import com.zqy.blog_admin.system.mapper.RoleMapper;
import com.zqy.blog_admin.system.response.AjaxResult;
import com.zqy.blog_admin.system.vo.RoleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author zqy
 * @since 2022-09-29
 */
@RestController
@RequestMapping("/system/role")
@Api(value = "/role",tags = "角色管理")
public class RoleController {

    @Resource
    private RoleMapper roleMapper;

    @PostMapping("create")

    @ApiOperation(value = "创建角色")
    public Map<String,Object> create(@RequestBody RoleVo roleVo){
        Role role = new Role();
        BeanUtils.copyProperties(roleVo,role);
        int insert = roleMapper.insert(role);
        HashMap<String, Object> map = new HashMap<>();
        map.put("code",200);
        map.put("success","ok");
        if (insert >0) {
            map.put("data",role.getId());
        } else {
            map.put("code",200);
            map.put("success","failed");
        }
        return map;
    }

    // 根据用户id查询角色
    @GetMapping("queryRoleById")
    @ApiOperation(value = "根据用户id查询角色")
    public AjaxResult queryRoleById(Long userId){

        Role roleItem = roleMapper.selectById(userId);


        return AjaxResult.success();
    }

}
