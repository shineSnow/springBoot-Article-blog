package com.zqy.blog_admin.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zqy.blog_admin.system.entity.*;
import com.zqy.blog_admin.system.mapper.*;
import com.zqy.blog_admin.system.response.AjaxResult;
import com.zqy.blog_admin.system.security.SecurityUser;
import com.zqy.blog_admin.system.service.UserService;
import com.zqy.blog_admin.system.utils.BeanMapTool;
import com.zqy.blog_admin.system.utils.BeanMapUtilByApache;
import com.zqy.blog_admin.system.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author zqy
 * @since 2022-09-28
 */
@RestController
@RequestMapping("/system/user")
@Api(value = "/user" ,tags = "用户管理")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Resource
    private PermissionMapper permissionMapper;

    // 注册
    @PostMapping(value = "register")
    @ApiOperation(value = "用户注册")
    public Map<String,Object> register (@RequestBody UserVo userVo){


        System.out.println("userVo"+userVo);
        User user = new User();
        BeanUtils.copyProperties(userVo,user);

        int insert = userMapper.insert(user);

        HashMap<String, Object> map = new HashMap<>();
        map.put("code",200);
        map.put("success",true);
        map.put("data", user.getId());

        return map;

    }

    // 分页查询用户列表
    @PreAuthorize("hasAnyRole('manager')")
    @GetMapping("list")
    @ApiOperation(value = "分页查询用户列表")
    public AjaxResult list(@RequestParam(defaultValue = "1L") Long current, @RequestParam(defaultValue = "10") Long size){

        Page<User> page = new Page<User>(current, size);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        Page<User> pageData = userMapper.selectPage(page, queryWrapper);
        List<User> userList = pageData.getRecords();



        ArrayList<Object> list = new ArrayList<>();
        for (User user : userList) {

            Role roleItem = roleMapper.selectById(user.getId());

            HashMap<Object, Object> roleMap = new HashMap<>();
            roleMap.put("id",roleItem.getId());
            roleMap.put("roleName",roleItem.getRoleName());
            roleMap.put("roleCode",roleItem.getRoleCode());
            roleMap.put("roleMark",roleItem.getRoleMark());

            HashMap<String, Object> mapUser = new HashMap<>();
            mapUser.put("id",user.getId());
            mapUser.put("userName",user.getUsername());
            mapUser.put("email",user.getEmail());
            mapUser.put("sex",user.getSex());
            mapUser.put("role",roleMap);
            list.add(mapUser);
        }

        HashMap<String, Object> map = new HashMap<>();

        map.put("list",list);
        map.put("current",pageData.getCurrent());
        map.put("size",pageData.getSize());
        map.put("total",pageData.getTotal());

        return AjaxResult.success(map);
    }

    // 查询用户基本信息
    @GetMapping("getUserInfo")
    @ApiOperation(value = "查询用户基本信息")
    public AjaxResult getUserInfo(Authentication authentication){
        Object principal =  authentication.getPrincipal();
        String username = ((SecurityUser) principal).getUsername();
        User userEntity = userService.findByUsername(username);
        // 查询用户基本信息
        User user = userMapper.selectById(userEntity.getId());

        QueryWrapper<UserRole> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("uid",user.getId());


        // 查询到用户的roleId
        UserRole userRole = userRoleMapper.selectById(user.getId());
        Role roleInfo = roleMapper.selectById(userRole.getRid());

        // 查询用户权限

        HashMap<String, Object> permQueryMap = new HashMap<>();

        permQueryMap.put("rid",userRole.getRid());

        List<RolePermission> rolePermissions = rolePermissionMapper.selectByMap(permQueryMap);

        ArrayList<Long> permissionIdList = new ArrayList<>();

        for (RolePermission rolePermission : rolePermissions) {
            permissionIdList.add(rolePermission.getPermId());
        }


        // 查询权限列表
//        List<Permission> permissions = permissionMapper.selectBatchIds(permissionIdList);
        QueryWrapper<Permission> permissionQueryWrapper = new QueryWrapper<>();
        permissionQueryWrapper.select(Permission.class,perm-> !perm.getColumn().equals("create_time") && !perm.getColumn().equals("update_time")).in("id",permissionIdList);

        List<Permission> permissions = permissionMapper.selectList(permissionQueryWrapper);

        ArrayList<Permission> rootMenuList = new ArrayList<>();

        for (Permission permission : permissions) {
            if (permission.getParentId() == 0) {
                permission.setChildren(getChildrenNode(permission.getId(),permissions));
                rootMenuList.add(permission);
            }
        }

        HashMap<String, Object> resMap = new HashMap<>();

        resMap.put("user",user);

        resMap.put("role",roleInfo);

        resMap.put("permissions",rootMenuList);

        return  AjaxResult.success(resMap);
    }


    /*
    * 递归获取子节点下的子节点
    * @param integer 父节点的ID
    * @param treesList 所有菜单树集合
    * @return
    * */

    private List<Permission> getChildrenNode(Long parentId,List<Permission> treeList){
        ArrayList<Permission> newTreeList = new ArrayList<>();

        for (Permission permission : treeList) {

            if (permission.getParentId() == 0) {
                continue;
            }

            if (permission.getParentId() == parentId) {
                // 递归获取子节点下的子节点，即设置树控件中的children
                List<Permission> childrenNode = getChildrenNode(permission.getId(), treeList);
                permission.setChildren(childrenNode);
                newTreeList.add(permission);
            }
        }
        return newTreeList;
    }

}
