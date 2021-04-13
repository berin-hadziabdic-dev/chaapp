package com.profile_messages.profile_messages.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;

@Data
@Entity(name="message")
@Table(name="message")
public class Message {
    @Id
    private Integer id;
    private String message;
    private Integer conversation_id;
    
    @Column(insertable=true,updatable=false,nullable=false)
    String sender_username;
}
