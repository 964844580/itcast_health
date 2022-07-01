package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.MemberDao;
import com.itheima.pojo.Member;
import com.itheima.service.MemberService;
import com.itheima.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(interfaceClass = MemberService.class)
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Override
    public Member getMemberByTelephone(String phoneNumber) {
        return memberDao.getMemberByTelephone(phoneNumber);
    }

    @Override
    public void add(Member member) {
        if (member.getPassword() != null) {
            member.setPassword(MD5Utils.md5(member.getPassword()));
        }
        memberDao.add(member);
    }

    @Override
    public List<Integer> getMemberCountByMonths(List<String> months) {
        String[] bigMonths = {"01", "03", "05", "07", "08", "10", "12"};
        List<Integer> memberCount = new ArrayList<>();
        for (String month : months) {
            String date = month + ".30";
            for (String bigMonth : bigMonths) {
                String m = month.substring(month.length() - 2);
                if (m.equals("02")) {
                    Integer year = Integer.parseInt(month.substring(0, 4));
                    if ((year == 0 % 4 && year % 100 != 0) || (0 == year % 400)) {//判断是否为闰年
                        date = month + ".29";
                    } else {
                        date = month + ".28";
                    }
                    break;
                }
                if (m.equals(bigMonth)) {
                    date = month + ".31";
                    break;
                }
            }
            Integer count = memberDao.getMemberCountBeforeDate(date);
            memberCount.add(count);
        }
        return memberCount;
    }

    @Override
    public Integer getMemberCountByDate(String date) {
        return memberDao.getMemberCountByDate(date);
    }

    @Override
    public Integer getTotalMemberCount() {
        return memberDao.getTotalMemberCount();
    }

    @Override
    public Integer getMemberCountAfterDate(String date) {
        return memberDao.getMemberCountAfterDate(date);
    }

}
