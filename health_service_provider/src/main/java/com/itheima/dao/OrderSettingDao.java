package com.itheima.dao;

import com.itheima.pojo.OrderSetting;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

public interface OrderSettingDao {
    /**
     * 根据日期查找此预约项是否存在
     * @param orderDate
     * @return
     */
    public long findCountByOrderDate(@Param("orderDate") Date orderDate);

    /**
     * 修改预约人数指定日期的
     * @param orderSetting
     */
    public void editNumberByOrderDate(OrderSetting orderSetting);

    /**
     * 添加预约设置信息
     * @param orderSetting
     */
    public void add(OrderSetting orderSetting);

    /**
     * 查询本月的预约数据
     * @param begin
     * @param end
     * @return
     */
    public List<OrderSetting> getOrderSettingByMonth(@Param("begin") String begin, @Param("end") String end);

    /**
     * 查询指定日期的预约数据
     * @param date
     * @return
     */
    public OrderSetting getOrderSettingByOrderDate(@Param("orderDate") Date date);

    /**
     * 通过日期修改预约人数
     * @param orderSetting
     */
    public void editReservationsByOrderDate(OrderSetting orderSetting);
}
