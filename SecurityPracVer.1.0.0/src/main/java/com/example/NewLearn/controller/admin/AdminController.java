package com.example.NewLearn.controller.admin;

import com.example.NewLearn.dto.member.MemberDTO;
import com.example.NewLearn.dto.paging.Criteria;
import com.example.NewLearn.dto.paging.PageDTO;
import com.example.NewLearn.service.member.BoardMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Controller
@RequiredArgsConstructor
public class AdminController {

    private final BoardMemberService boardMemberService;

    // [회원] 전체 회원 조회
    @GetMapping("/admin/member")
    public String selectAllMember(Criteria cri, Model model, Authentication auth) {
        List<MemberDTO> memberDTOS = new ArrayList<>();

        log.info("::::[ controller 관리자 ]:::: 전체회원 조회  :::::::::::: ");

        memberDTOS = boardMemberService.selectAllMember(cri);

        model.addAttribute("auth", auth);
        model.addAttribute("result", memberDTOS);
        model.addAttribute("pageMaker", new PageDTO(cri, boardMemberService.getTotal()));

        return "admin/adminMemberList";

    }
    // [회원] 체크된 회원 삭제
    @PostMapping("/admin/member/delete")
    public String deleteMember(@RequestParam("delete") List<String> ids){

        log.info(" [관리자] 선택 회원 삭제 ::::::::::: ");
        if (ids != null) {
            for(String idStr : ids){
                int id = Integer.parseInt(idStr);
                boardMemberService.memberDelete(id);
            }
        }
        return "redirect:/admin/member";
    }



}
