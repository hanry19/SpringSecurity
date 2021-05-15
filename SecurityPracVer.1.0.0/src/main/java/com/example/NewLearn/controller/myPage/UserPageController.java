package com.example.NewLearn.controller.myPage;

import com.example.NewLearn.dto.member.MemberDTO;
import com.example.NewLearn.dto.paging.Criteria;
import com.example.NewLearn.dto.paging.PageDTO;
import com.example.NewLearn.service.member.BoardMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/mypage")
public class UserPageController {

    private final BoardMemberService boardMemberService;

    // 전체 회원 리스트 조회
    @GetMapping({"/select"})
    public String selectAllMember(Criteria cri, Model model) {
        List<MemberDTO> memberDTOS = new ArrayList<>();

        log.info(":::::::::::: 전체회원 조회  in controller :::::::::::: ");

        memberDTOS = boardMemberService.selectAllMember(cri);

        model.addAttribute("result", memberDTOS);
        model.addAttribute("pageMaker", new PageDTO(cri, boardMemberService.getTotal()));

        return "memberList/memberList";
    }

    // 회원 상세 조회
    @GetMapping("/detail/{no}")
    public String selectMemberDetail(@PathVariable("no") int no, Model model) {
        log.info("::::::::::: 회원 상세조회  in controller ::::::::::::::");

        model.addAttribute("detail", boardMemberService.selectDetailMember(no));
        return "memberList/memberDetail";
    }

    // 회원 정보 수정
    @GetMapping("/update/{no}")
    public String updateMember(@PathVariable int no, Model model) {

        log.info("::::::::::: 회원 정보 수정  in controller :::::::::::::::::");

        model.addAttribute("update", boardMemberService.selectDetailMember(no));

        return "memberList/memberUpdate";
    }

    // 회원 정보 수정 완료
    @PostMapping("/update")
    public String updateMember(@ModelAttribute MemberDTO memberDTO) {

        log.info("::::::::::: 회원 정보 수정 완료 in controller  :::::::::::::::::::");
        boardMemberService.memberUpdate(memberDTO);
        String no = Long.toString(memberDTO.getNo());

        return "redirect:/mypage/detail/"+no;
    }
    // 회원 탈퇴
    @PostMapping("/delete/{no}")
    public String deleteMember(@PathVariable int no) {

        log.info("::::::::::: 회원 탈퇴 ::::::::::::::::::::::::");
        boardMemberService.memberDelete(no);

        return "redirect:/mypage/select";
    }

}
