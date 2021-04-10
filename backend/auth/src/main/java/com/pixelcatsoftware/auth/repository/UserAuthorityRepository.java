package com.pixelcatsoftware.auth.repository;

import java.util.List;

import com.pixelcatsoftware.auth.entity.UserAuthority;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthorityRepository extends JpaRepository<UserAuthority,Integer> {

    public List<UserAuthority> findAllUserAuthorityByUsername(String username);
    
}
