package com.itheima.service;

import com.itheima.pojo.Role;

import java.util.Set;

public interface RoleService {
    /**
     * 根据用户Id查询查询对应角色
     * @param userId
     * @return
     */
    public Set<Role> findByUserId(Integer userId);
}
