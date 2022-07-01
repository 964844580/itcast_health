package com.itheima.dao;

import com.itheima.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

public interface RoleDao {
    /**
     * 根据用户id查询对应的角色
     * @param userId
     * @return
     */
    public Set<Role> findByUserId(@Param("userId") Integer userId);
}
