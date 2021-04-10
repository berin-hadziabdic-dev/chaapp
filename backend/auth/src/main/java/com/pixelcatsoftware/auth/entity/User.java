package com.pixelcatsoftware.auth.entity;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Entity
@Table(name="users")
@Data
@Cacheable
public class User  {

    @Id
    String username;
    @Column(nullable=false,insertable = true,updatable = true)
    String password_;
    @Column(unique = true)
    String email;

    
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="username", referencedColumnName = "username")
    List<UserAuthority> usersAuthority;

    public List<? extends GrantedAuthority> getAuthorities(){
        return usersAuthority;
    }


    
}
