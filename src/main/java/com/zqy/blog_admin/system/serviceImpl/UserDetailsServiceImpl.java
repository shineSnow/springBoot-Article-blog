package com.zqy.blog_admin.system.serviceImpl;

import com.zqy.blog_admin.system.entity.*;
import com.zqy.blog_admin.system.security.SecurityUser;
import com.zqy.blog_admin.system.service.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.PortResolverImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserService userService;

    @Resource
    private UserRoleService userRoleService;

    @Resource
    private RoleService roleService;

    @Resource
    private RolePermissionService rolePermissionService;

    @Resource
    private PermissionService permissionService;



    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        // 从数据库中取出用户信息
        User user = userService.findByUsername(s);

        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        // 添加权限
        List<UserRole> userRoles = userRoleService.listByUserId(user.getId());

        for (UserRole userRole : userRoles) {
            Role role = roleService.findById(userRole.getRid());
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleCode()));

            ArrayList<Long> permIdList = new ArrayList<>();
            // 根据roleId查询所有permId
            List<RolePermission> rolePermissionList = rolePermissionService.findPermsByRoleId(role.getId());

            for (RolePermission rolePermission : rolePermissionList) {
                permIdList.add(rolePermission.getPermId());
            }

            List<Permission> permissionList = permissionService.listByIds(permIdList);

            for (Permission permission : permissionList) {
                authorities.add(new SimpleGrantedAuthority(permission.getPermission()));
            }

        }

        return new SecurityUser(user.getUsername(),user.getPassword(),authorities);
    }
}
