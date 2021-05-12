package com.example.NewLearn.mapper.member;

import com.example.NewLearn.dto.member.MemberDTO;
import com.example.NewLearn.dto.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMemberMapper {

    // 회원 전체 조회 (페이징 포함)
    List<MemberDTO> selectAllMember(Criteria cri);
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



}
