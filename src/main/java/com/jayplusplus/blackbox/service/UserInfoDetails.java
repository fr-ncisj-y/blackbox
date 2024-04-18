package com.jayplusplus.blackbox.service;

import com.jayplusplus.blackbox.entity.UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type User info details.
 */
public class UserInfoDetails implements UserDetails {
    /**
     * The User name.
     */
    String userName = null;
    /**
     * The Password.
     */
    String password = null;
    /**
     * The Authorities.
     */
    List<GrantedAuthority> authorities;

    /**
     * Instantiates a new User info details.
     *
     * @param userInfo the user info
     */
    public UserInfoDetails(UserInfo userInfo){
        userName=userInfo.getUsername();
        password=userInfo.getPassword();
        authorities= Arrays.stream(userInfo.getRoles().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
