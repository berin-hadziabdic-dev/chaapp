package com.profile_messages.profile_messages.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="profile_contact")
@Table(name="profile_contact")
@Data @NoArgsConstructor
public class ProfileContact {
    @Id
    Integer profile_contact_id;
    @Column(nullable=false,insertable=true,updatable=false)
    private String owning_username;
    @JoinColumn(name="contact_username")
    @ManyToOne(fetch = FetchType.EAGER)
    private Profile contact;

}
