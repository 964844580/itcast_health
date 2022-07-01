package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.CheckGroupDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckGroup;
import com.itheima.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = CheckGroupService.class)
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    private CheckGroupDao checkGroupDao;

    @Override
    public void add(CheckGroup checkGroup, Integer[] checkItemIds) {
        checkGroupDao.add(checkGroup);
        for (Integer checkItemId : checkItemIds) {
            checkGroupDao.setCheckGroupAndCheckItem(checkGroup.getId(),checkItemId);
        }
    }

    @Override
    public PageResult PageQuery(QueryPageBean queryPageBean) {
        //开启分页查询
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        Page<CheckGroup> page=checkGroupDao.findByCondition(queryPageBean.getQueryString());
        //返回分页查询结果
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public CheckGroup findById(Integer id) {
        return checkGroupDao.findById(id);
    }

    @Override
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id) {
        return checkGroupDao.findCheckItemIdsByCheckGroupId(id);
    }

    @Override
    public void edit(CheckGroup checkGroup, Integer[] checkItemIds) {
        //修改检查组的基本信息
        checkGroupDao.edit(checkGroup);
        //删除检查组所包含的检查项
        checkGroupDao.deleteAssociation(checkGroup.getId());
        //重新设置检查组所包含的检查项
        for (Integer checkItemId : checkItemIds) {
            checkGroupDao.setCheckGroupAndCheckItem(checkGroup.getId(),checkItemId);
        }
    }

    @Override
    public void delete(Integer id) {
        //删除此检查组关联的检查项信息
        checkGroupDao.deleteAssociation(id);
        //删除检查组基本信息
        checkGroupDao.delete(id);
    }

    @Override
    public List<CheckGroup> findAll() {
        return checkGroupDao.findAll();
    }
}
