package com.example.NewLearn.security.config;

import com.example.NewLearn.security.handlers.AuthFailureHandler;
import com.example.NewLearn.security.handlers.AuthProvider;
import com.example.NewLearn.security.handlers.AuthSuccessHandler;
import com.example.NewLearn.service.security.SecurityService;
import com.example.NewLearn.service.security.SecurityServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
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
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthSuccessHandler authSuccessHandler;
    private final AuthFailureHandler authFailureHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }




    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/user/**").access("hasRole('ROLE_USER')")
//                .antMatchers("/manager/**").access("hasRole('ROLE_MANAGER')")
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")

                .antMatchers("/mypage/**").access("hasRole('ROLE_USER')or hasRole('ROLE_ADMIN')")

                //????????? ????????? ??? ?????? ??????????????? ????????? ????????? ????????? ???????????? (.permitAll())
                .antMatchers("/main",
                        "/login",
                        "/sign-Up",
                        "/pwFind",
                        "/login/**",
                        "/oauth/**",
                        "/oauth2/**",
                        "/pwFind/**",
                        "/css/**",
                        "/vendor/**",
                        "/logout"

                ).permitAll()

                //?????? /** ??? ????????? ????????? ????????? ??????
                .antMatchers("/**").authenticated();



        http.formLogin()
                // ????????? ??? ????????? ?????????
                .loginPage("/login")
                // ????????? ?????? ?????? ??? ????????? ???????????? ??????
                .loginProcessingUrl("/login-process")
                .usernameParameter("email")    //input name ??????????????? "email"??? ?????????.
                .passwordParameter("password") //input name ??????????????? "password"??? ?????????.
                .failureHandler(authFailureHandler) //????????? ????????? ???????????? ?????????
                .successHandler(authSuccessHandler);// ????????? ????????? ???????????? ?????????



        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")  // ??? ????????? ??????????????? ??????
                .invalidateHttpSession(true);   // ?????? ?????????

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers( "/css","/js" );
    }
}
