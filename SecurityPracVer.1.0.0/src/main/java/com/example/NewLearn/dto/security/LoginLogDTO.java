package com.example.NewLearn.dto.security;

import com.example.NewLearn.dto.member.MemberDTO;
import lombok.Data;

@Data
public class LoginLogDTO extends MemberDTO {

    private String loginIp;
    private String loginDate;


}
