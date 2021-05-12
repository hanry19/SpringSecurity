package com.example.NewLearn.security.config;

import com.example.NewLearn.security.handlers.AuthFailureHandler;
import com.example.NewLearn.security.handlers.AuthProvider;
import com.example.NewLearn.security.handlers.AuthSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;
    @Autowired
    AuthProvider authProvider;
    @Autowired
    AuthSuccessHandler authSuccessHandler;
    @Autowired
    AuthFailureHandler authFailureHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean           //  For remember-me Logic Not yet finish.
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);
        return repo;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
//                .antMatchers("/user/**").access("hasRole('ROLE_USER')")
//                .antMatchers("/manager/**").access("hasRole('ROLE_MANAGER')")
//                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")

                //누구나 접속할 수 있는 페이지이기 때문에 누구나 접근이 가능하다 (.permitAll())
                .antMatchers("/main",
                        "/admin/mypage",
                        "/login",
                        "/join",
                        "/join/idCheck",
                        "/login/find",
                        "/join/insert").permitAll()

                //기타 /** 의 경로는 인증을 필요로 한다
                .antMatchers("/**").authenticated();

        http.formLogin()
                // connect to login page via below url
                .loginPage("/signUp")
                // Path of the login button when pressed
                .loginProcessingUrl("login/login-processing")

                .usernameParameter("email")    //input name 파라미터로 "email"를 받는다.
                .passwordParameter("password") //input name 파라미터로 "password"를 받는다.
                .failureHandler(authFailureHandler) //로그인 실패시 수행하는 클래스
                .successHandler(authSuccessHandler); // 로그인 성공시 수행하는 클래스

        http.logout()
                //logout 경로로 요청이 들어올 경우 이 경로로 리다이렉트 하고 세션 초기화
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")  // 이 경로로 리다이렉트 하고
                .invalidateHttpSession(true);   // 세션 초기화


        http.rememberMe()
                .key("zerock")
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(604800);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers( "/css","/js" );
    }
}
