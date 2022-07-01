package com.itheima.service;

import com.itheima.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderSettingService {
    /**
     * 批量添加预约管理数据
     * @param orderSettingList
     */
    public void add(List<OrderSetting> orderSettingList);

    /**
     * 查询本月的预约数据
     * @param date
     * @return
     */
    public List<Map> getOrderSettingByMonth(String date);

    /**
     * 设置可预约人数
     * @param orderSetting
     */
    public void editNumberByDate(OrderSetting orderSetting);

    /**
     * 根据日期查找预约数据
     * @param date
     * @return
     */
    public OrderSetting getOrderSettingByOrderDate(Date date);

    /**
     * 通过日期修改预约人数
     * @param orderSetting
     */
    public void editReservationsByOrderDate(OrderSetting orderSetting);
}
