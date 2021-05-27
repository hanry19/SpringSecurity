package com.example.NewLearn.mapper.member;

import com.example.NewLearn.dto.member.MemberDTO;
import com.example.NewLearn.dto.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMemberMapper {


    // 관리자 : 회원 전체 조회
    List<MemberDTO> selectAllMember(Criteria cri);

    // 회원 : 로그인 해당 계정 정보 조회
    List<MemberDTO> selectMember(String email);

    //전체 글 갯수 조회
    int getTotal();

    // 회원 상세 조회
    MemberDTO selectDetailMember(int no);

    //회원 수정
    int memberUpdate(MemberDTO memberDTO);

    // 회원 삭제
    int memberDelete(int no);

    // 동적검색 테스트
    List<MemberDTO> searchTest(Map<String, Map<String, String>> map);

    //아이디 존재여부
    String checkId(String email);

    // 임시 비번
    int tempPw(MemberDTO memberDTO);



}
