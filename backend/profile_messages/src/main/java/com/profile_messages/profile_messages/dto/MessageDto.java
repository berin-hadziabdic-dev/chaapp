package com.profile_messages.profile_messages.dto;

import com.profile_messages.profile_messages.entities.Message;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class MessageDto {

    private Integer id;
    private String message;
    private Integer chat_id;
    private String sender_username;

    public MessageDto(Message msg)
    {
        this.id = msg.getId();
        this.message = msg.getMessage();
        this.chat_id = msg.getChat_id();
        this.sender_username = msg.getSender_username();
    }
}
