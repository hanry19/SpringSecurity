package com.example.NewLearn.security.handlers;

import com.example.NewLearn.dto.member.MemberDTO;
import com.example.NewLearn.service.security.SecurityService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
public class AuthProvider implements AuthenticationProvider {

    private static final Logger logger = LoggerFactory.getLogger(AuthSuccessHandler.class);

    private final SecurityService securityService;

    //로그인 버튼을 누를 경우
    //실행 1

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        return authenticate(email, password);
    }

    //실행 2
    private Authentication authenticate (String email, String password) throws  AuthenticationException {


        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        MemberDTO member = new MemberDTO();

        member = (MemberDTO) securityService.loadUserByUsername(email);

        if (member == null) {
            logger.info("::::::::: 회원정보 없다!!!::::::::");
            throw new UsernameNotFoundException(email);
        } else if (member != null && !passwordEncoder.matches(password, member.getPassword())) {
            logger.info(":::::: 비번 틀렷다 ::::::");
            throw new BadCredentialsException(email);
        }

        grantedAuthorities.add(new SimpleGrantedAuthority(member.getUserRole()));

        logger.info(" ㅇㅋㅇㅋ 통과 통과 ");

        return new MyAuthentication(email, password, grantedAuthorities, member);

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
