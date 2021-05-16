package com.example.NewLearn.dto.member;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO implements UserDetails {

    private int no;
    private String email;
    private String password;
    private String name;
    private String role;
    private int passwordLock;
    private Date regDate;
    private String status;


    /*UserDetails 기본 상속 변수 */
    private Collection<? extends GrantedAuthority> authorities;
    private boolean isEnabled = true;
    private String username;
    private boolean isCredentialsNonExpired = true;
    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;




}
