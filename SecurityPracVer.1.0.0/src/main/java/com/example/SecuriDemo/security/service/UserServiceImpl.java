package com.example.SecuriDemo.security.service;

import com.example.SecuriDemo.login.dao.LoginMapper;
import com.example.SecuriDemo.vo.LoginLog;
import com.example.SecuriDemo.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl implements SecurityService {

    @Autowired
    PasswordEncoder passwordEncoder;


    @Autowired
    LoginMapper loginMapper;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Member member = loginMapper.getSelectMemberInfo(id);
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (member != null) {
            authorities.add(new SimpleGrantedAuthority(member.getUserRole()));
            member.setAuthorities(authorities);

        }
        return member;
    }

    @Override
    public int setInsertMember(Member member) throws Exception {
        member.setPassword(passwordEncoder.encode(member.getPassword()));

        return loginMapper.setInsertMember(member);
    }

    @Override
    public Member getSelectMemberInfo(String id) throws Exception {
        return loginMapper.getSelectMemberInfo(id);
    }

    @Override
    public int setUpdatePasswordLockCnt(String id) throws Exception {
        return loginMapper.setUpdatePasswordLockCnt(id);
    }

    @Override
    public int setUpdatePasswordLockCntReset(String id) throws Exception {
        return loginMapper.setUpdatePasswordLockCntReset(id);
    }

    @Override
    public int setInsertLoginLog(LoginLog loginLog) throws Exception {
        return loginMapper.setInsertLoginLog(loginLog);
    }
}
