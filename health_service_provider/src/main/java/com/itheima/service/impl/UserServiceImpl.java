package com.itheima.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.UserDao;
import com.itheima.pojo.Permission;
import com.itheima.pojo.Role;
import com.itheima.pojo.User;
import com.itheima.service.PermissionService;
import com.itheima.service.RoleService;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service(interfaceClass = UserService.class)
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;
    @Override
    public User findByUsername(String username) {
        User user = userDao.findByUsername(username);
        if (user==null) return null;
        Set<Role> roles = roleService.findByUserId(user.getId());
        for (Role role : roles) {
            Set<Permission> permissions = permissionService.findByRoleId(role.getId());
            role.setPermissions(permissions); //角色关联权限
        }
        user.setRoles(roles); //用户关联角色

        return user;
    }
}
