package com.profile_messages.profile_messages.repositories;

import com.profile_messages.profile_messages.entities.Chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<Chat,Integer>{
}
