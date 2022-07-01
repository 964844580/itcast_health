package com.itheima.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.OrderSettingDao;
import com.itheima.pojo.OrderSetting;
import com.itheima.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {
    @Autowired
    private OrderSettingDao orderSettingDao;

    @Override
    public void add(List<OrderSetting> orderSettingList) {
        //判断list集合是否为空
        if (orderSettingList != null && orderSettingList.size() > 0) {
            for (OrderSetting orderSetting : orderSettingList) {
                //检查此数据（日期）是否存在
                long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
                if (count > 0) {
                    //已经存在，执行更新操作
                    orderSettingDao.editNumberByOrderDate(orderSetting);
                } else {
                    //不存在，执行添加操作
                    orderSettingDao.add(orderSetting);
                }
            }
        }
    }
    @Override
    public List<Map> getOrderSettingByMonth(String date) {
        String[] bigMonths={"01","03","05","07","08","10","12"};
        String begin=date+"-1";
        String end=date+"-30";
        for (String bigMonth : bigMonths) {
            String month=date.substring(date.length()-2);
            if (month.equals("02")){
                Integer year = Integer.parseInt(month.substring(0, 4));
                if ((year == 0 % 4 && year % 100 != 0) || (0 == year % 400)) {
                    date = month + "-29";
                } else {
                    date = month + "-28";
                }
                break;
            }
            if (month.equals(bigMonth)){
                end=date+"-31";
                break;
            }
        }
        List<OrderSetting> orderSettingList=orderSettingDao.getOrderSettingByMonth(begin,end);
        List<Map> resultList=new ArrayList<>();
        if (orderSettingList!=null && orderSettingList.size()>0){
            for (OrderSetting orderSetting : orderSettingList) {
                Map<String,Object> map=new HashMap<>();
                map.put("date",orderSetting.getOrderDate().getDate());
                map.put("number",orderSetting.getNumber());
                map.put("reservations",orderSetting.getReservations());
                resultList.add(map);
            }
        }
        return resultList;
    }

    @Override
    public void editNumberByDate(OrderSetting orderSetting) {
        //需要设置的日期是否存在
        long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
        if (count>0){
            //当前日期已经进行了预约设置，需要进行修改操作
            orderSettingDao.editNumberByOrderDate(orderSetting);
        }else{
            //当前日期没有进行预约设置，进行添加操作
            orderSettingDao.add(orderSetting);
        }
    }

    @Override
    public OrderSetting getOrderSettingByOrderDate(Date date) {
        return orderSettingDao.getOrderSettingByOrderDate(date);
    }

    @Override
    public void editReservationsByOrderDate(OrderSetting orderSetting) {
        orderSettingDao.editReservationsByOrderDate(orderSetting);
    }
}
