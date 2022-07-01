package com.itheima.service;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckItem;

import java.util.List;


public interface CheckItemService {
    /**
     * 添加检查项
     * @param checkItem
     */
    public void add(CheckItem checkItem);

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    public PageResult findPage(QueryPageBean queryPageBean);

    /**
     * 根据Id删除检查项
     * @param id
     */
    public void deleteById(Integer id);

    /**
     * 编辑检查项
     * @param checkItem
     */
    public void edit(CheckItem checkItem);

    /**
     * 根据Id查询检查项
     * @param id
     * @return
     */
    public CheckItem findById(Integer id);

    /**
     * 查询所有的检查项
     * @return
     */
    public List<CheckItem> findAll();
}
