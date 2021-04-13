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
    @JoinColumn(name="username",referencedColumnName = "owning_username")
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<ContactDetails> contacts; 
}
