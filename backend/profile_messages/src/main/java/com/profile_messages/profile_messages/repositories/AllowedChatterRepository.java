package com.profile_messages.profile_messages.repositories;

import com.profile_messages.profile_messages.entities.AllowedChatter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllowedChatterRepository extends JpaRepository<AllowedChatter,Integer>{
    
}
