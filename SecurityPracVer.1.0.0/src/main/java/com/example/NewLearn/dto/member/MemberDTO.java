package com.example.NewLearn.dto.member;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO implements UserDetails {

    private long no;
    private String email;
    private String password;
    private String name;
    private String phone;
    private String userRole;
    private String gitUrl;
    private int totalAmount;
    private int passwordLock;
    private Date modDate;
    private Date dateReg;
    private String status;


    /*UserDetails 기본 상속 변수 */
    private Collection<? extends GrantedAuthority> authorities;
    private boolean isEnabled = true;
    private String username;
    private boolean isCredentialsNonExpired = true;
    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;






}