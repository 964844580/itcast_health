package com.itheima.service;

import com.itheima.pojo.Member;

import java.util.List;

public interface MemberService {
    /**
     * 根据手机号码来查询会员
     * @param telephone
     * @return
     */
    public Member getMemberByTelephone(String telephone);

    /**
     * 新增会员
     * @param member
     */
    public void add(Member member);

    /**
     * 根据月份查询会员数
     * @param months
     * @return
     */
    public List<Integer> getMemberCountByMonths(List<String> months);

    /**
     * 查询指定日期新增的会员数
     * @param reportDate
     * @return
     */
    public Integer getMemberCountByDate(String reportDate);

    /**
     * 查询全部的会员数
     * @return
     */
    public Integer getTotalMemberCount();

    /**
     * 查询指定日期之后注册的会员数
     * @param date
     * @return
     */
    public Integer getMemberCountAfterDate(String date);
}
