package com.example.NewLearn.service.security;

import com.example.NewLearn.dto.member.MemberDTO;
import com.example.NewLearn.dto.security.LoginLogDTO;
import com.example.NewLearn.mapper.login.LoginMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    private final LoginMapper loginMapper;

    // 시큐리티 사용자 인증
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        MemberDTO member = loginMapper.selectMemberInfo(email);
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (member != null) {
            authorities.add(new SimpleGrantedAuthority(member.getUserRole()));
            member.setAuthorities(authorities);

        }
        return member;
    }


    @Override
    public int memberSignUp(MemberDTO member) throws Exception {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        member.setPassword(passwordEncoder.encode(member.getPassword()));

        return loginMapper.memberSignUp(member);
    }

    @Override
    public MemberDTO selectMemberInfo(String email) {
        return null;
    }

    @Override
    public MemberDTO memberIdCheck(String email) throws Exception {
        return loginMapper.selectMemberInfo(email);
    }

    @Override
    public int passwordFailCnt(String email) throws Exception {
        return loginMapper.passwordFailCnt(email);
    }

    @Override
    public int resetPasswordFailCnt(String email) throws Exception {
        return loginMapper.resetPasswordFailCnt(email);
    }

    @Override
    public int AddLoginLog(LoginLogDTO loginLogDTO) throws Exception {
        return loginMapper.AddLoginLog(loginLogDTO);
    }

}
