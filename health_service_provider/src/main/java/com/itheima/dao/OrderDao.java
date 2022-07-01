package com.itheima.dao;

import com.itheima.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderDao {

    /**
     * 条件查询预约订单
     * @param order
     * @return
     */
    public List<Order> selectByCondition(Order order);

    /**
     * 新增预约订单
     * @param order
     */
    public void add(Order order);

    /**
     * 查询详细预约信息
     * @param id
     * @return
     */
    public Map findById4Detail(@Param("id") Integer id);

    /**
     * 查询指定日期的预约数
     * @param date
     * @return
     */
    public Integer getOrderCountByDate(@Param("date") String date);

    /**
     * 查询指定日期之后的预约数
     * @param date
     * @return
     */
    public Integer getOrderCountAfterDate(@Param("date") String date);

    /**
     * 本日导致数
     * @param date
     * @return
     */
    public Integer getVisitsCountByDate(@Param("date") String date);

    /**
     * 指定日期之后的到诊数
     * @param date
     * @return
     */
    public Integer getVisitsCountAfterDate(@Param("date") String date);

    /**
     * 获取热门套餐
     * @return
     */
    public List<Map> findHotSetmeal();
}
