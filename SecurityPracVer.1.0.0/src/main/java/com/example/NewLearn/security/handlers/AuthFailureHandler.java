package com.example.NewLearn.security.handlers;

import com.example.NewLearn.dto.security.LoginLogDTO;
import com.example.NewLearn.service.security.SecurityService;
import com.example.NewLearn.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    SecurityService securityService;

    private static final Logger logger = LoggerFactory.getLogger(AuthFailureHandler.class);


    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        String ip = CommonUtil.getClientIp(request);
        logger.info("::::::::: login fail ::::::::::");
        logger.info("@@@ 로그인 실패 아이피  "+ip);
        LoginLogDTO loginLogDTO = new LoginLogDTO();
        String email = "";
        String msg = "";

        try {
            email = exception.getMessage();

            if (securityService.selectMemberInfo(email) != null) {
                securityService.passwordFailCnt(email);

                loginLogDTO.setLoginIp(ip);
                loginLogDTO.setEmail(email);
                loginLogDTO.setStatus("failure");
                securityService.AddLoginLog(loginLogDTO);
                msg = "password miss matching";
            } else {
                msg = "can not found E-mail";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/login?msg=" + msg);

    }

}
