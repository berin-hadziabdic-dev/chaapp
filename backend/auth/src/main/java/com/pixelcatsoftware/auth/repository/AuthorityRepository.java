package com.pixelcatsoftware.auth.repository;

import com.pixelcatsoftware.auth.entity.Authority;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority,Integer> {
    
}
