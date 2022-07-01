package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.constant.MessageConstant;
import com.itheima.dao.OrderDao;
import com.itheima.entity.Result;
import com.itheima.pojo.Member;
import com.itheima.pojo.Order;
import com.itheima.pojo.OrderSetting;
import com.itheima.service.MemberService;
import com.itheima.service.OrderService;
import com.itheima.service.OrderSettingService;
import com.itheima.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = OrderService.class)
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderSettingService orderSettingService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private OrderDao orderDao;

    @Override
    public Result order(Map<String, Object> map) throws Exception {
        //1、检查用户所选择的预约日期是否已经提前进行了预约设置，如果没有设置则无法进行预约
        //获取预约日期
        String orderDate = (String) map.get("orderDate");
        //转换格式
        Date date = DateUtils.parseString2Date(orderDate);
        //查询此日期的预约的基本信息
        OrderSetting orderSetting = orderSettingService.getOrderSettingByOrderDate(date);
        if (orderSetting == null) {
            //指定日期没有进行预约设置，无法进行预约
            return new Result(false, MessageConstant.SELECTED_DATE_CANNOT_ORDER);
        }
        //2、检查用户所选择的预约日期是否已经约满，如果已经约满则无法预约
        if (orderSetting.getReservations()>=orderSetting.getNumber()){
            //预约已满，无法预约
            return new Result(false,MessageConstant.ORDER_FULL);
        }
        //3、检查用户是否重复预约（同一个用户在同一天预约了同一个套餐），如果是重复预约则无法完成再次预约
        String telephone = (String) map.get("telephone");//获取用户页面获取的手机号
        //查询到指定手机号的会员
        Member member = memberService.getMemberByTelephone(telephone);
        if (member!=null){
            //判断是否在重复预约
            String setmealId = (String) map.get("setmealId");//获取预定的套餐id
            List<Order> orderList =orderDao.selectByCondition(new Order(member.getId(),date,Integer.parseInt(setmealId)));
            if (orderList!=null && orderList.size()>0){
                //已经完成了预约，不能重复预约
                return new Result(false,MessageConstant.HAS_ORDERED);
            }
        }else{
            //4、检查当前用户是否为会员，如果是会员则直接完成预约，如果不是会员则自动完成注册并进行预约
            //当前用户不是会员，需要添加到会员表
            member = new Member();
            member.setName((String) map.get("name"));
            member.setPhoneNumber(telephone);
            member.setIdCard((String) map.get("idCard"));
            member.setSex((String) map.get("sex"));
            member.setRegTime(new Date());
            memberService.add(member);
        }
        //5、预约成功，更新当日的已预约人数
        Order order = new Order(member.getId(), date, (String)map.get("orderType"), Order.ORDERSTATUS_NO, Integer.parseInt((String) map.get("setmealId")));
        orderDao.add(order);
        orderSetting.setReservations(orderSetting.getReservations()+1);

        orderSettingService.editReservationsByOrderDate(orderSetting);

        return new Result(true,MessageConstant.ORDER_SUCCESS,order.getId());
    }

    @Override
    public Map findById(Integer id) throws Exception {
        Map map = orderDao.findById4Detail(id);
        if(map != null){
            //处理日期格式
            Date orderDate = (Date) map.get("orderDate");
            map.put("orderDate",DateUtils.parseDate2String(orderDate));
        }
        return map;
    }

    @Override
    public Integer getOrderCountByDate(String date) {
        return orderDao.getOrderCountByDate(date);
    }

    @Override
    public Integer getOrderCountAfterDate(String date) {
        return orderDao.getOrderCountAfterDate(date);
    }

    @Override
    public Integer getVisitsCountByDate(String date) {
        return orderDao.getVisitsCountByDate(date);
    }

    @Override
    public Integer getVisitsCountAfterDate(String date) {
        return orderDao.getVisitsCountAfterDate(date);
    }

    @Override
    public List<Map> findHotSetmeal() {
        return orderDao.findHotSetmeal();
    }
}
