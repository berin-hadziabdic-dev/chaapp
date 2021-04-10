package com.pixelcatsoftware.auth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;



@Entity
public class Authority implements GrantedAuthority {
    @Id
    @Getter
    private Integer authority_id;
    @Column(updatable=false,insertable=true,nullable=false)
    private String authority_description;
    @Override
    public String getAuthority() {
        return this.authority_description;
    }
}
