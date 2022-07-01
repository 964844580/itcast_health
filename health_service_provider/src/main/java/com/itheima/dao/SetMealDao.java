package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.Setmeal;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SetMealDao {

    /**
     * 添加检查套餐
     * @param setmeal
     */
    public void add(Setmeal setmeal);

    /**
     * 设置此套餐所包含的检查组
     * @param setMealId
     * @param checkGroupId
     */
    public void setSetMealAndCheckGroup(@Param("setMealId") Integer setMealId,@Param("checkGroupId") Integer checkGroupId);

    /**
     * 分页条件查询
     * @param queryString
     * @return
     */
    public Page<Setmeal> queryPage(@Param("queryString") String queryString);

    /**
     * 根据Id查询套餐信息
     * @param id
     * @return
     */
    public Setmeal findById(@Param("id") Integer id);

    /**
     * 根据Id查询检查套餐中包含的检查组的id
     * @param id
     * @return
     */
    public List<Integer> findCheckGroupIdsBySetMealId(@Param("id") Integer id);

    /**
     * 编辑更新检查套餐
     * @param setmeal
     */
    public void edit(Setmeal setmeal);

    /**
     * 删除检查套餐所包含的检查组
     * @param id
     */
    public void deleteAssociation(@Param("id") Integer id);

    /**
     * 删除此检查套餐的基本信息
     * @param id
     */
    public void delete(Integer id);

    /**
     * 查询所有套餐信息
     * @return
     */
    public List<Setmeal> getAllSetMeal();

    /**
     * 查询套餐的详细信息
     * @param id
     * @return
     */
    public Setmeal findDetailById(@Param("id") int id);

    /**
     * 获取套餐统计数据
     * @return
     */
    public List<Map<String, Object>> getSetMealCount();
}
