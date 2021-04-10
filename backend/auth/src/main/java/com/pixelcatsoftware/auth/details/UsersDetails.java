package com.pixelcatsoftware.auth.details;

import java.util.Collection;
import java.util.List;

import com.pixelcatsoftware.auth.dto.UsernamePasswordDto;
import com.pixelcatsoftware.auth.entity.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UsersDetails implements UserDetails {

    private String username;
    private String password;
    private List<? extends GrantedAuthority> usersGrantedAuthorities;

    public UsersDetails(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword_();
        this.usersGrantedAuthorities = user.getUsersAuthority();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return this.usersGrantedAuthorities;
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return this.password;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
    
}
