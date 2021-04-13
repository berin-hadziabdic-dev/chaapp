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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Data
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
    @JoinColumn(name="user_one", referencedColumnName="username",updatable=false,nullable=true)
    @ManyToOne(fetch = FetchType.EAGER)
    private Profile userOneProfile;
    @JoinColumn(name="user_two", referencedColumnName="username",updatable=false,nullable=true)
    @ManyToOne(fetch = FetchType.EAGER)
    private Profile userTwoProfile;
}
