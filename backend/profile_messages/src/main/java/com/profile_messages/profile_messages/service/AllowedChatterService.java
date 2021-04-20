package com.profile_messages.profile_messages.service;

import java.util.Optional;

import com.profile_messages.profile_messages.entities.AllowedChatter;
import com.profile_messages.profile_messages.entities.Chat;
import com.profile_messages.profile_messages.exceptions.BadRequestException;
import com.profile_messages.profile_messages.matchers.StringMatcher;
import com.profile_messages.profile_messages.repositories.AllowedChatterRepository;
import com.profile_messages.profile_messages.repositories.ChatRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AllowedChatterService {

    AllowedChatterRepository allowedChatterRepo;
    StringMatcher matcher;
   
    public void addChatterToChat(Chat existing_chat,String username) 
    {
        AllowedChatter newChatter = new AllowedChatter();
        newChatter.setChat_id(existing_chat.getChat_id());
        newChatter.setOwning_username(username);
        this.allowedChatterRepo.save(newChatter);
    }

    public void removeChatterFromChat(String session_username,Integer participant_id)
    {
        AllowedChatter chatter = this.findChatter(participant_id);

        if(!this.matcher.match(session_username, chatter.getOwning_username()))
            throw new BadRequestException("The chatter attempted to delete a chat permisson they do not own.");
        
        this.allowedChatterRepo.delete(chatter);
    }

    public AllowedChatter findChatter(Integer participant_id)
    {
        Optional<AllowedChatter> discovered_chatter = this.allowedChatterRepo.findById(participant_id);
        AllowedChatter chatter = null;

        if(!discovered_chatter.isPresent())
            throw new BadRequestException("The user requested a chat participant that did not exist.");

        chatter = discovered_chatter.get();

        return chatter;
    }

    

    
}
