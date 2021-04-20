package com.profile_messages.profile_messages.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String message;
    private Integer chat_id;
    
    
    @Column(insertable=true,updatable=false,nullable=false)
    String sender_username;

    /**This constructor is used to create a new message. Per system requirements, the username is 
     * always extracted from the session so even though the dto has a username, we should use the String 
     * username that was extracted from the Session to prevent impostors from sending messages.
     */
    public Message(MessageDto dto)
    {
        this.sender_username = dto.getSender_username();
        this.message = dto.getMessage();
        this.chat_id=dto.getChat_id();
    }

    
}
