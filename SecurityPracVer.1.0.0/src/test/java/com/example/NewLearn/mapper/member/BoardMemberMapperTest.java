package com.example.NewLearn.mapper.member;

import com.example.NewLearn.dto.member.MemberDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Rollback(value = false)
class BoardMemberMapperTest {


    @Autowired
    BoardMemberMapper boardMemberMapper;


    @Test
    void test() {
        MemberDTO memberDTO = new MemberDTO();

        memberDTO.setPassword("123123");
        memberDTO.setEmail("123");
        memberDTO.setPhone("010101010101010");
        memberDTO.setBlogUrl("www.naver.com");
        memberDTO.setGitUrl("www.daum.net");
        memberDTO.setId("hsj3");

        boardMemberMapper.memberUpdate(memberDTO);

        System.out.println(boardMemberMapper.selectDetailMember(3));
    }


    @Test
    @DisplayName("Searching test")
    public void testSearch() {
        Map<String, String> map = new HashMap<>();
        map.put("T", "TTT");
        map.put("C", "CCC");

        Map<String, Map<String, String>> outer = new HashMap<>();
        outer.put("map", map);

        List<MemberDTO> list = boardMemberMapper.searchTest(outer);

        System.out.println("list = " + list);

    }


}