package com.profile_messages.profile_messages.entities;

import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.profile_messages.profile_messages.dto.AllowedParticipantDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="chat_participant")
@Table(name="chat_participant")
@Data() @NoArgsConstructor
public class AllowedChatter {

    public AllowedChatter(String username, Integer chat_id)
    {
        this.owning_username = username;
        this.chat_id = chat_id;
    }
    public AllowedChatter(AllowedParticipantDto participant) {
        this.chat_id = participant.getChat_id();
        this.owning_username = participant.getOwning_username();
    }
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Integer chat_participant_id;
    @Column(nullable = false, insertable = true, updatable = false)
    private Integer chat_id;
    @Column(nullable = false, insertable = true, updatable = false)
    private String owning_username;  
}
