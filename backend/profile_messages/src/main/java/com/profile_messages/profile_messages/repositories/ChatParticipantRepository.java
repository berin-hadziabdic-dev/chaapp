package com.profile_messages.profile_messages.repositories;

import com.profile_messages.profile_messages.entities.AllowedChatter;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatParticipantRepository extends JpaRepository<AllowedChatter,String>{
    
}
