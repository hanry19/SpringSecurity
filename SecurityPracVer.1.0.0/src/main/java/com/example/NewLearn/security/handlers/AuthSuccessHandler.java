package com.example.NewLearn.security.handlers;

import com.example.NewLearn.dto.security.LoginLogDTO;
import com.example.NewLearn.service.security.SecurityService;
import com.example.NewLearn.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/*
 *
2021-05-14 10:16:26.477  INFO 19240 --- [  restartedMain] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 2866 ms
 * 스프링 시큐리티를 사용하며, 로그인 성공시 부가 작업을 하려면,
 * org.springframework.security.web.authentication.AuthenticationSuccessHandler를 구현해야 한다.
 *별도로 authenticationSuccessHandler를 지정하지 않으면 기본적으로
 * org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler를 사용하게 된다.
 */


@Component
public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(AuthSuccessHandler.class);

    @Autowired
    SecurityService securityServiceMapper;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {




        String ip = CommonUtil.getClientIp(request);

        logger.info("# AuthSuccessHandler 3번"+ip);
        logger.info("로그인 아이피  "+ip);
        logger.info("::::::::::::::: 로그인 성공 !!! ::::::::::::::::");

        String email = "";

        try {
            email = authentication.getName().toString();
            String getDetails = authentication.getDetails().toString();
            String getAuthorities = authentication.getAuthorities().toString();
            String s = authentication.getPrincipal().toString();


            System.out.println("email = " + email);
            System.out.println("a = " + getDetails);
            System.out.println("b = " + getAuthorities);
            System.out.println("s = " + s);

            securityServiceMapper.resetPasswordFailCnt(email);


        } catch (Exception e) {
            e.printStackTrace();
        }

        super.setDefaultTargetUrl("/mypage/select");
        super.onAuthenticationSuccess(request, response, authentication);


    }
}
