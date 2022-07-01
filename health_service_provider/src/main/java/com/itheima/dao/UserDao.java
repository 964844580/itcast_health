package com.itheima.dao;

import com.itheima.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    /**
     * 根据用户名查询用户基本信息
     * @param username
     * @return
     */
    public User findByUsername(@Param("username") String username);
}
