package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.CheckItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CheckGroupDao {

    /**
     * 添加检查组
     * @param checkGroup
     */
    public void add(CheckGroup checkGroup);

    /**
     * 设置检查组包含的检查项
     */
    public void setCheckGroupAndCheckItem(@Param("checkGroupId") Integer checkGroupId ,@Param("checkItemId") Integer CheckItemId);

    /**
     * 条件查询返回分页对象
     * @param queryString
     * @return
     */
    public Page<CheckGroup> findByCondition(@Param("queryString") String queryString);

    /**
     * 根据Id查询检查组
     * @param id
     * @return
     */
    public CheckGroup findById(@Param("id") Integer id);

    /**
     * 根据Id查询检查组包含的检查项的id
     * @param id
     * @return
     */
    List<Integer> findCheckItemIdsByCheckGroupId(@Param("id") Integer id);

    /**
     * 更新检查组的基本信息
     * @param checkGroup
     */
    public void edit(CheckGroup checkGroup);

    /**
     * 删除此检查项所包含的检查组的guanlian
     * @param checkGroupId
     */
    public void deleteAssociation(@Param("checkGroupId") Integer checkGroupId);

    /**
     * 根据id删除检查组的基本信息
     * @param id
     */
    public void delete(@Param("id") Integer id);

    /**
     * 查询所有的检查组
     * @return
     */
    public List<CheckGroup> findAll();

    /**
     * 查询套餐所包含的检查组的详细信息
     * @param setMealId
     * @return
     */
    public List<CheckGroup> findDetailBySetMealId(@Param("setMealId") Integer setMealId);



}
