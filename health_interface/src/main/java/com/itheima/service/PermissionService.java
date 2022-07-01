package com.itheima.service;

import com.itheima.pojo.Permission;

import java.util.Set;

public interface PermissionService {
    /**
     * 根据角色id来查询对应的权限
     * @param roleId
     * @return
     */
    public Set<Permission> findByRoleId(Integer roleId);
}
