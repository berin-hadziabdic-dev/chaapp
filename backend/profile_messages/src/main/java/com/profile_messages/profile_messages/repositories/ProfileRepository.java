package com.profile_messages.profile_messages.repositories;

import com.profile_messages.profile_messages.entities.Profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile,String> {
    
}
