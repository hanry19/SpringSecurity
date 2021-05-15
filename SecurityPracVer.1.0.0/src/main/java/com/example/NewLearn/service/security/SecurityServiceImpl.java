package com.example.NewLearn.service.security;

import com.example.NewLearn.dto.member.MemberDTO;
import com.example.NewLearn.dto.paging.Criteria;
import com.example.NewLearn.dto.security.LoginLogDTO;
import com.example.NewLearn.mapper.login.LoginMapper;
import com.example.NewLearn.mapper.member.BoardMemberMapper;
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

    private final BoardMemberMapper boardMemberMapper;
    private final LoginMapper loginMapper;

    @Override
    public List<MemberDTO> selectAllMember(Criteria cri) {
        log.info("여기는 select all member 서비스 단!!!! ");
        return boardMemberMapper.selectAllMember(cri);
    }

    @Override
    public int getTotal() {
        log.info("여기는 get total 서비스 단!!!! ");
        return boardMemberMapper.getTotal();
    }

    // 시큐리티 사용자 인증
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("::::::::::: 사용자 인증 ::::::::::::::::::");

        MemberDTO member = loginMapper.selectMemberInfo(email);
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (member != null) {
            authorities.add(new SimpleGrantedAuthority(member.getUserRole()));
            member.setAuthorities(authorities);

        }
        return member;
    }


    @Override
    public int memberSignUp(MemberDTO memberDTO) throws Exception {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDTO.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
        log.info(":::::::::::회원가입 서비스 단 ::::::::::::::::::");

        return loginMapper.memberSignUp(memberDTO);
    }

    @Override
    public MemberDTO selectMemberInfo(String email) {
        return loginMapper.selectMemberInfo(email);
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
