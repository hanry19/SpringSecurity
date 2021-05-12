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
@RequestMapping({"/admin/mypage", "/"})
public class MyPageController {

    private final BoardMemberService boardMemberService;

    @GetMapping({"/", ""})
    public String selectAllMember(Criteria cri, Model model) {
        List<MemberDTO> memberDTOS = new ArrayList<>();

        log.info("Mypage Select All Member........................");

        memberDTOS = boardMemberService.selectAllMember(cri);

        model.addAttribute("result", memberDTOS);
        model.addAttribute("pageMaker", new PageDTO(cri, boardMemberService.getTotal()));

        return "memberList/memberList";
    }

    @GetMapping("/detail/{no}")
    public String selectMemberDetail(@PathVariable("no") int no, Model model) {
        log.info("Mypage Select All Detail........................");

        model.addAttribute("detail", boardMemberService.selectDetailMember(no));
        return "memberList/memberDetail";
    }

    @GetMapping("/update/{no}")
    public String updateMember(@PathVariable int no, Model model) {

        log.info("Mypage Select All Request Update Page........................");

        model.addAttribute("update", boardMemberService.selectDetailMember(no));

        return "memberList/memberUpdate";
    }

    @PostMapping("/update")
    public String updateMember(@ModelAttribute MemberDTO memberDTO) {

        log.info("Mypage Select All Update Submits.......................");
        boardMemberService.memberUpdate(memberDTO);
        String no = Long.toString(memberDTO.getNo());

        return "redirect:/mypage/detail/"+no;
    }

    @PostMapping("/delete/{no}")
    public String deleteMember(@PathVariable int no) {

        log.info("Mypage Select All Request Delete Page........................");
        boardMemberService.memberDelete(no);

        return "redirect:/mypage";
    }

}
