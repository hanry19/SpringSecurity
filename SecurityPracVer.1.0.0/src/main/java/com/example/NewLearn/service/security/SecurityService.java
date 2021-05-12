package com.example.NewLearn.service.security;

import com.example.NewLearn.dto.member.MemberDTO;
import com.example.NewLearn.dto.security.LoginLogDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface SecurityService extends UserDetailsService {

    // 회원정보조회
    MemberDTO selectMemberInfo(String id);
    //회원가입
    int memberSignUp(MemberDTO member)throws Exception;
    // 시큐리티 사용자 인증
    UserDetails loadUserByUsername(String email);
    // 중복 email 체크
    MemberDTO memberIdCheck(String email) throws Exception;
    // 비밀번호 틀린 횟수 증가
    int passwordFailCnt(String email) throws Exception;
    // 비밀번호 틀린 횟수 초기화
    int resetPasswordFailCnt(String email) throws Exception;
    /* 로그인 로그 */
    int AddLoginLog(LoginLogDTO loginLogDTO) throws Exception;


}
