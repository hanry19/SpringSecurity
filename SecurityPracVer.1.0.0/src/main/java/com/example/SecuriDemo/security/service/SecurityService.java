package com.example.SecuriDemo.security.service;

import com.example.SecuriDemo.vo.LoginLog;
import com.example.SecuriDemo.vo.Member;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface SecurityService extends UserDetailsService {

    // 시큐리티 사용자 인증
    UserDetails loadUserByUsername(String id);
    // 중복아이디 체크
    Member getSelectMemberInfo(String id) throws Exception;
    //회원가입
    int setInsertMember(Member member)throws Exception;
    // 비밀번호 틀린 횟수 증가
    int setUpdatePasswordLockCnt(String id) throws Exception;
    // 비밀번호 틀린 횟수 초기화
    int setUpdatePasswordLockCntReset(String id) throws Exception;

    /* 로그인 로그 */
    int setInsertLoginLog(LoginLog loginLog) throws Exception;
}
