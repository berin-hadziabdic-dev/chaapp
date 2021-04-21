package com.profile_messages.profile_messages.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.profile_messages.profile_messages.dto.AllowedParticipantDto;
import com.profile_messages.profile_messages.dto.ChatDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data  @NoArgsConstructor
@Entity(name="chat")
@Table(name="chat")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer chat_id;
    @JoinColumn(referencedColumnName = "chat_id", name="chat_id")
    @OneToMany(fetch = FetchType.LAZY)
    private List<Message> chatMessages;
    @JoinColumn(referencedColumnName = "chat_id",name="chat_id")
    @OneToMany()
    private List<AllowedChatter> allowedParticipants;

    public Chat(ChatDto dto)
    {
        for(AllowedParticipantDto participant: dto.getParticipants())
           {
             this.allowedParticipants.add(new AllowedChatter(participant));
           }
        
    }
    
}
