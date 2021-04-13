package com.pixelcatsoftware.auth.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name="user_authority")
public class UserAuthority implements GrantedAuthority  {
    @Id
    Integer authority_id;

    @Column(nullable = false)
    private String username;
    @JoinColumn(name="authority_key",referencedColumnName = "authority_id")
    @OneToOne
    private Authority authority;
  

  @Override
  public String getAuthority() {
      // TODO Auto-generated method stub
      return authority.getAuthority();
  }
    
}
