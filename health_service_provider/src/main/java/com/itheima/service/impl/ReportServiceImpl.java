package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.service.MemberService;
import com.itheima.service.OrderService;
import com.itheima.service.ReportService;
import com.itheima.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = ReportService.class)
@Transactional
public class ReportServiceImpl implements ReportService {
    @Autowired
    private MemberService memberService;
    @Autowired
    private OrderService orderService;

    @Override
    public Map<String, Object> getBusinessReportData() {
        Map<String, Object> result = new HashMap<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        //本日日期
        String reportDate = simpleDateFormat.format(DateUtils.getToday());
        //本周一日期
        String thisWeekMonday = simpleDateFormat.format(DateUtils.getThisWeekMonday());
        //本月第一天的日期
        String firstDayThisMonth = simpleDateFormat.format(DateUtils.getFirstDay4ThisMonth());
        //本日新增会员数
        Integer todayNewMember = memberService.getMemberCountByDate(reportDate);
        //总会员数
        Integer totalMember = memberService.getTotalMemberCount();
        //本周新增会员数
        Integer thisWeekNewMember = memberService.getMemberCountAfterDate(thisWeekMonday);
        //本月新增会员数
        Integer thisMonthNewMember = memberService.getMemberCountAfterDate(firstDayThisMonth);
        //本日的预约数
        Integer todayOrderNumber = orderService.getOrderCountByDate(reportDate);
        //本周预约数
        Integer thisWeekOrderNumber = orderService.getOrderCountAfterDate(thisWeekMonday);
        //本月预约数
        Integer thisMonthOrderNumber = orderService.getOrderCountAfterDate(firstDayThisMonth);
        //今日到诊数
        Integer todayVisitsNumber = orderService.getVisitsCountByDate(reportDate);
        //本周到诊数
        Integer thisWeekVisitsNumber = orderService.getVisitsCountAfterDate(thisWeekMonday);
        //本月到诊数
        Integer thisMonthVisitsNumber = orderService.getVisitsCountAfterDate(firstDayThisMonth);
        //热门套餐（取前2）
        List<Map> hotSetmeal = orderService.findHotSetmeal();
        result.put("reportDate", reportDate);
        result.put("todayNewMember", todayNewMember);
        result.put("totalMember", totalMember);
        result.put("thisWeekNewMember", thisWeekNewMember);
        result.put("thisMonthNewMember", thisMonthNewMember);
        result.put("todayOrderNumber", todayOrderNumber);
        result.put("thisWeekOrderNumber", thisWeekOrderNumber);
        result.put("thisMonthOrderNumber", thisMonthOrderNumber);
        result.put("todayVisitsNumber", todayVisitsNumber);
        result.put("thisWeekVisitsNumber", thisWeekVisitsNumber);
        result.put("thisMonthVisitsNumber", thisMonthVisitsNumber);
        result.put("hotSetmeal", hotSetmeal);
        return result;
    }
}
