package com.profile_messages.profile_messages.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.profile_messages.profile_messages.dto.ConversationRecordDto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The conversation record class connects a user to a Conversation. 
 * A user can participate in many conversations.
 */
@Data @NoArgsConstructor
@Entity(name="conversation_record")
@Table(name="conversation_record")
public class ConversationRecord {
    @Id 
    private Integer id;
    @Column(nullable = false,updatable = false)
    private String owning_username;
    @JoinColumn(name="conversation_id",referencedColumnName = "conversation_id")
    private Conversation storedConversation;

    public ConversationRecord(ConversationRecordDto dto,Conversation convo)
    {
        this.owning_username = dto.getOwning_username();
        this.storedConversation = convo;
    }

    
}
