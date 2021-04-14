package com.profile_messages.profile_messages.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.profile_messages.profile_messages.dto.ConversationDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@Entity(name="conversation")
@Table(name="conversation")
public class Conversation 
{   
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer conversation_id;
    @JoinColumn(name="conversation_id",referencedColumnName = "conversation_id")
    @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Message> messages;
    @Transient
    //@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL,mappedBy="conversation_id")
    private List<ConversationRecord> conversationParticipants; //List of conversation participants

       
    /** This constructor is only invoked when creating a new conversation. 
     * @param dto the dto to create the Conversation from.
     */
    public Conversation(ConversationDto dto)
    {
        this.messages = null;
    }
}
