package com.example.NewLearn.controller.security;

import com.example.NewLearn.dto.member.MemberDTO;
import com.example.NewLearn.service.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping("/all")
public class LoginController {

    @Autowired
    SecurityService securityService;

    //회원가입 페이지 이동
    @GetMapping("/signUp")
    public String join() {
        return  "login/join";
    }
    //로그인 페이지 이동
    @RequestMapping("/login")
    public String lgoin() {
        return "login/login";
    }
    //로그인 에러페이지 이동
    @GetMapping("/login/login-error")
    public String error() {
        return  "login/error";
    }

    //아이디 중복체크
    @PostMapping("/join/idCheck")
    @ResponseBody
    public String idCheck(@RequestParam String email) throws Exception{
        MemberDTO memberDTO = securityService.selectMemberInfo(email);
        String canUse = memberDTO != null ? "" : "Y";
        return canUse;
    }

    //회원가입 Insert
    @PostMapping("/signUp")
    public String setInsertMember(MemberDTO memberDTO) throws Exception{
        if(securityService.memberSignUp(memberDTO) > 0){
            return  "login/login";
        }else {
            return  "join/join";
        }
    }
}
