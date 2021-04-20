package com.profile_messages.profile_messages.dto;

import com.profile_messages.profile_messages.entities.AllowedChatter;

import lombok.Data;

@Data
public class AllowedParticipantDto {
    private Integer chat_participant_id;
    private String owning_username;  
    private Integer chat_id;

    /**T */
    public AllowedParticipantDto(AllowedChatter chatter) 
    {
        this.owning_username = chatter.getOwning_username();
    }
  
}
