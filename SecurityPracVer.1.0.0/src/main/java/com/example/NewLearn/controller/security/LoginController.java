package com.example.NewLearn.controller.security;

import com.example.NewLearn.dto.member.MemberDTO;
import com.example.NewLearn.service.security.SecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Log4j2
@RequiredArgsConstructor
public class LoginController {

    private final SecurityService securityService;


    //회원가입 GET
    @GetMapping("/sign-Up")
    private String join(@ModelAttribute MemberDTO memberDTO) {

        log.info(":::::::::::  회원가입  in controller ::::::::::::::::::");

        return "login/join";
    }
    //회원가입 Insert
    @PostMapping("/sign-Up")
    private String setInsertMember(@ModelAttribute MemberDTO memberDTO) throws Exception{
        log.info("::::::::::: 회원가입 전송 in controller ::::::::::::::::::");
        securityService.memberSignUp(memberDTO);
        return "forward:/login";
    }

    //로그인 GET
    @RequestMapping("/login")
    private String login() {
        log.info(":::::::::::  로그인  in controller ::::::::::::::::::");
        return "login/login";
    }

    //로그인 에러페이지 이동
    @GetMapping("/login/login-error")
    private String error() {

        log.info(":::::::::::  로그인 에러페이지 in controller ::::::::::::::::::");

        return  "login/error";
    }

    /*    // 로그인 성공 이동 그냥 바로 메인으로 쏘으면 될듯? 필요 없음
    @GetMapping("/main")
    private String loginSuccess() {
        return  "main/home";
    }*/


    //아이디 중복체크
    @PostMapping("/join/idCheck")
    @ResponseBody
    private String idCheck(@RequestParam String email) throws Exception{
        MemberDTO memberDTO = securityService.selectMemberInfo(email);
        String canUse = memberDTO != null ? "" : "Y";
        return canUse;
    }


}
