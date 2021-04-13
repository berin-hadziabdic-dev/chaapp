package com.profile_messages.profile_messages.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity(name="contact_details")
@Table(name="contact_details")
public class ContactDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
  
    @JoinColumn(name="contact_username", referencedColumnName = "username")
    @ManyToOne(fetch=FetchType.EAGER)
    private Profile connectedUser;
}
