package com.profile_messages.profile_messages.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

import com.profile_messages.profile_messages.dto.MessageDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@Entity(name="message")
@Table(name="message")
public class Message {
    @Id
    private Integer id;
    private String message;
    private Integer conversation_id;
    
    @Column(insertable=true,updatable=false,nullable=false)
    String sender_username;

    public Message(MessageDto dto)
    {
        this.id = dto.getId();
        this.conversation_id = dto.getConversation_id();
        this.message = dto.getMessage();
        this.sender_username = dto.getSender_username();
    }
}
