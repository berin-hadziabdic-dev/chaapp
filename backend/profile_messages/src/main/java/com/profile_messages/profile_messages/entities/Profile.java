package com.profile_messages.profile_messages.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.profile_messages.profile_messages.dto.ProfileDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@Entity(name="profile")
@Table(name="profile")
public class Profile 
{
    @Id
    private String username;
    @Column(nullable=false,updatable=true,insertable=true)
    private String email;
    @Column(nullable=false,updatable=true,insertable=true)
    private Integer area_code;
    @Column(nullable=false,updatable=true,insertable=true)
    private Integer phone_number;
    @Transient
    @JoinColumn(name="username",referencedColumnName = "owning_username")
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ContactDetails> contacts; 
    @Transient
    @JoinColumn(name="username", referencedColumnName = "owning_username")
    @OneToMany(fetch = FetchType.LAZY)
    private List<ConversationRecord> usersConversations;

    public Profile(ProfileDto dto)
    {
        this.username = dto.getUsername();
        this.email = dto.getEmail();
        this.area_code = dto.getArea_code();
        this.phone_number = dto.getPhone_number();
    }
}
