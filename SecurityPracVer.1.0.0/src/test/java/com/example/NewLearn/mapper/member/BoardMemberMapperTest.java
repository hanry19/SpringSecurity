package com.example.NewLearn.mapper.member;

import com.example.NewLearn.dto.member.MemberDTO;
import com.example.NewLearn.service.security.SecurityService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.Rollback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Rollback(value = false)
class BoardMemberMapperTest {


    @Autowired
    BoardMemberMapper boardMemberMapper;

    @Autowired
    SecurityService securityServiceMapper;


    @Test
    void test() {
        MemberDTO memberDTO = new MemberDTO();

        memberDTO.setPassword("123123");
        memberDTO.setEmail("123");

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

    @Test
    @DisplayName("asd")
    public void testMapper() {

        MemberDTO memberDTO = securityServiceMapper.selectMemberInfo("hanry18@gmail.com");
        System.out.println("memberDTO = " + memberDTO);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();

//        System.out.println("email = " +  auth.getName());



//        memberDTO.getAuthorities().forEach(grantedAuthority -> System.out.println("grantedAuthority = " + grantedAuthority));

    }

    @Test
    public void testt() {
        Authentication auth =SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();


    }
}