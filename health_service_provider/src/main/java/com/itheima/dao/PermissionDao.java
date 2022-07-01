package com.itheima.dao;

import com.itheima.pojo.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

public interface PermissionDao {

    /**
     * 根据角色id查询对应的权限
     *
     * @param roleId
     * @return
     */
   public Set<Permission> findByRoleId(@Param("roleId") Integer roleId);
}
