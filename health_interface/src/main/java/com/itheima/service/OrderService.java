package com.itheima.service;

import com.itheima.entity.Result;

import java.util.List;
import java.util.Map;

public interface OrderService {
    /**
     * 体检预约
     * @param map
     * @return
     */
    public Result order(Map<String,Object> map) throws Exception;

    /**
     * 查询预约信息
     * @param id
     * @return
     */
    public Map<String, Object> findById(Integer id) throws Exception;

    /**
     * 查询指定日期的预约数
     * @param date
     * @return
     */
    public Integer getOrderCountByDate(String date);

    /**
     * 查询指定日期之后的预约数
     * @param date
     * @return
     */
    public Integer getOrderCountAfterDate(String date);

    /**
     * 本日到诊数
     * @param date
     * @return
     */
    public Integer getVisitsCountByDate(String date);

    /**
     * 指定日期之后的到诊数
     * @param date
     * @return
     */
    public Integer getVisitsCountAfterDate(String date);

    /**
     * 获取热门套餐
     * @return
     */
    public List<Map> findHotSetmeal();
}
