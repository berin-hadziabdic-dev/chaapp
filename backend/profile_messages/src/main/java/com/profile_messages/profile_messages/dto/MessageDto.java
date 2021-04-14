package com.profile_messages.profile_messages.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.profile_messages.profile_messages.entities.Message;

import lombok.Data;

@Data
public class MessageDto {
    @NotNull @Min(0)
    private Integer id;
    @NotNull 
    private String message;
    @NotNull
    private Integer conversation_id;
    @NotNull @NotBlank    
    String sender_username;

    public MessageDto(Message message)
    {
        this.id = message.getId();
        this.conversation_id = message.getConversation_id();
        this.message = message.getMessage();
        this.sender_username = message.getSender_username();
    }
}
