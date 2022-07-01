package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckGroup;

import java.util.List;

public interface CheckGroupService {
    /**
     * 添加检查组
     * @param checkGroup
     * @param checkItemIds
     */
    public void add(CheckGroup checkGroup,Integer[] checkItemIds);

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    public PageResult PageQuery(QueryPageBean queryPageBean);

    /**
     * 根据Id查询检查组
     * @param id
     * @return
     */
    public CheckGroup findById(Integer id);

    /**
     * 根据Id查询检查组包含的检查项的Id
     * @param id
     * @return
     */
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    /**
     * 编辑检查项
     * @param checkGroup
     * @param checkItemIds
     */
    public void edit(CheckGroup checkGroup, Integer[] checkItemIds);

    /**
     * 根据id删除指定的检查组
     * @param id
     */
    public void delete(Integer id);

    /**
     * 查询所有的检查组
     * @return
     */
    public List<CheckGroup> findAll();
}
