package com.itheima.dao;
import com.itheima.pojo.CheckItem;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface CheckItemDao {
    /**
     * 添加检查项
     * @param checkItem
     */
    public void add(CheckItem checkItem);

    /**
     * 分页条件查询
     * @return
     */
    public List<CheckItem> selectByCondition(@Param("queryString") String queryString,@Param("startRecord") Integer startRecord,@Param("pageSize") Integer pageSize);

    /**
     * 查询总记录数
     * @return
     */
    public Long getTotalCount();

    /**
     * 根据Id查询检查组表中包含此检查项的条数
     * @param id
     * @return
     */
    public long findCheckItemCountFromCheckGroupById(Integer id);

    /**
     * 根据Id删除指定的检查项
    * @param id
     */
    public void deleteById(@Param("id") Integer id);

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
    public CheckItem findById(@Param("id") Integer id);

    /**
     * 查询所有的检查项
     * @return
     */
    public List<CheckItem> findAll();

    /**
     * 查询检查组所包含的检查项
     * @param checkGroupId
     * @return
     */
    public List<CheckItem> findByCheckGroupId(@Param("checkGroupId") Integer checkGroupId);
}
