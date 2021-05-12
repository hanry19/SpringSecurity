package com.example.NewLearn.service.member;

import com.example.NewLearn.dto.member.MemberDTO;
import com.example.NewLearn.dto.paging.Criteria;

import java.util.List;

public interface BoardMemberService {

    // 회원 전체 조회
    List<MemberDTO> selectAllMember(Criteria cri);

    //전체 글 수량 조회
    int getTotal();

    // 회원 상세 조회
    MemberDTO selectDetailMember(int no);

    //회원 수정
    int memberUpdate(MemberDTO memberDTO);

    // 회원 삭제
    int memberDelete(int no);





}
