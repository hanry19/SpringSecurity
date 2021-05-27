package com.example.NewLearn.controller.mail;

import com.example.NewLearn.dto.member.MemberDTO;
import com.example.NewLearn.service.mail.MailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
public class MailController {
    private final MailService mailService;

    @GetMapping("/pwFind")
    public String dispMail() {

        return "memberList/memberFind";
    }

    @PostMapping("/pwFind")
    public String execMail(MemberDTO memberDTO) {

        mailService.checkId(memberDTO);
        return "redirect:/mypage/select";
    }
}