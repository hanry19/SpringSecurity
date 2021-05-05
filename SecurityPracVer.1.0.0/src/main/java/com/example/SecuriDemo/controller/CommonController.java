package com.example.SecuriDemo.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
public class CommonController {

    @GetMapping("/accessError")
    public String accessDenied(Authentication auth, Model model) {
        log.info("access Denied : " + auth);
        model.addAttribute("msg", "access Denied");
        return "/sample/accessError";
    }

    @GetMapping("/customLogin")
    public String loginInput(String error, String logout, Model model) {
        log.info("error :" + error);
        log.info("logout : " + logout);

        if (error != null) {
            model.addAttribute("error", "login error check your account");
        }
        if (logout != null) {
            model.addAttribute("logout", "logout!!");

        }
            return "/sample/customLogin";
    }
}
