package com.example.NewLearn.security.handlers;

import com.example.NewLearn.dto.member.MemberDTO;
import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

//현재 로그인한 사용자 객체 저장 DTO

@Data
public class MyAuthentication extends UsernamePasswordAuthenticationToken {

    private static final long serialVersionUID = 1L;

    MemberDTO memberDTO;


    public MyAuthentication(String email, String password,
                            List<GrantedAuthority> grantedAuthorityList, MemberDTO member) {

        super(email, password, grantedAuthorityList);
        this.memberDTO = member;

    }
}
