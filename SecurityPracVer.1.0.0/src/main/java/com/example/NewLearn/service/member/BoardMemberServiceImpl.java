package com.example.NewLearn.service.member;

import com.example.NewLearn.dto.member.MemberDTO;
import com.example.NewLearn.dto.paging.Criteria;
import com.example.NewLearn.mapper.member.BoardMemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class BoardMemberServiceImpl implements BoardMemberService {

    private final BoardMemberMapper boardMemberMapper;

    @Override
    public List<MemberDTO> selectAllMember(Criteria cri) {
        log.info("여기는 select all member 서비스 단!!!! ");
        return boardMemberMapper.selectAllMember(cri);
    }

    @Override
    public int getTotal() {
        log.info("여기는 get total 서비스 단!!!! ");
        return boardMemberMapper.getTotal();
    }

    @Override
    public MemberDTO selectDetailMember(int no) {
        log.info("여기는 select detail member  서비스 단!!!");
        return boardMemberMapper.selectDetailMember(no);
    }

    @Override
    public int memberUpdate(MemberDTO memberDTO) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDTO.setPassword(passwordEncoder.encode(memberDTO.getPassword()));

        log.info("여기는 update 서비스 단!!!! ");
        return boardMemberMapper.memberUpdate(memberDTO);
    }

    @Override
    public int memberDelete(int no) {
        log.info("여기는 memberDelete  서비스 단!!!! ");
        return boardMemberMapper.memberDelete(no);
    }



}
