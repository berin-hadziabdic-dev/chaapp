package com.profile_messages.profile_messages.repositories;

import com.profile_messages.profile_messages.entities.Message;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<Message,Integer> {
    
}
