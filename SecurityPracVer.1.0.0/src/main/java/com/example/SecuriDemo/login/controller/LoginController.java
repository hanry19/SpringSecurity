package com.example.SecuriDemo.login.controller;

import com.example.SecuriDemo.security.service.SecurityService;
import com.example.SecuriDemo.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @Autowired
    SecurityService securityService;

    //회원가입 페이지 이동
    @GetMapping("/join/join")
    public String join() {
        return  "views/login/join";
    }

    //로그인 페이지 이동
    @RequestMapping("/login/login")
    public ModelAndView lgoin(@RequestParam(value="msg", required=false) String msg) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg",msg);
        mv.setViewName("views/login/login");
        return mv;
    }

    //로그인 에러페이지 이동
    @GetMapping("/login/login-error")
    public String error() {
        return  "views/login/error";
    }

    //아이디 중복체크
    @PostMapping("/join/idCheck")
    @ResponseBody
    public String idCheck(@RequestParam String inputId) throws Exception{
        Member member = securityService.getSelectMemberInfo(inputId);
        String canUse = member != null ? "" : "Y";
        return canUse;
    }

    //회원가입 Insert
    @PostMapping("/join/insert")
    public String setInsertMember(Member member) throws Exception{
        if(securityService.setInsertMember(member) > 0){
            return  "views/login/login";
        }else {
            return  "views/join/join";
        }
    }

}