package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.Setmeal;

import java.util.List;
import java.util.Map;

public interface SetMealService {
    /**
     * 新增检查套餐
     * @param setmeal
     * @param checkGroupIds
     */
    public void add(Setmeal setmeal, Integer[] checkGroupIds);

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    public PageResult queryPage(QueryPageBean queryPageBean);

    /**
     * 根据Id查询套餐信息
     * @param id
     * @return
     */
    public Setmeal findById(Integer id);

    /**
     * 根据Id查询检查套餐中包含的检查组
     * @param id
     * @return
     */
    public List<Integer> findCheckGroupIdsBySetMealId(Integer id);

    /**
     * 编辑检查套餐
     * @param setmeal
     * @param checkGroupIds
     */
    public void edit(Setmeal setmeal, Integer[] checkGroupIds);

    /**
     * 根据Id删除检查套餐
     * @param id
     */
    public void delete(Integer id,String imgName);

    /**
     * 查询所有套餐信息
     * @return
     */
    public List<Setmeal> getAllSetMeal();

    /**
     * 查询套餐详情信息
     * @param id
     * @return
     */
    public Setmeal findDetailById(int id);

    /**
     *
     *查询套餐数据
     * @return
     */
    public List<Map<String, Object>> getSetMealCount();
}
