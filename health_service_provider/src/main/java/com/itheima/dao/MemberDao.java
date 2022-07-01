package com.itheima.dao;

import com.itheima.pojo.Member;
import org.apache.ibatis.annotations.Param;

public interface MemberDao {
    /**
     * 根据手机号码来查询指定会员
     * @param phoneNumber
     * @return
     */
    public Member getMemberByTelephone(@Param("phoneNumber") String phoneNumber);

    /**
     * 新增会员
     * @param member
     */
    public void add(Member member);

    /**
     * 查询会员的数量
     * @param date
     * @return
     */
    public Integer getMemberCountBeforeDate(@Param("date") String date);

    /**
     * 查询指定日期的新增会员数
     * @param date
     * @return
     */
    public Integer getMemberCountByDate(@Param("date") String date);

    /**
     * 查询会员总数
     * @return
     */
    public Integer getTotalMemberCount();

    /**
     * 查询指定日期之后注册的会员数
     * @param date
     * @return
     */
    public Integer getMemberCountAfterDate(@Param("date") String date);
}
