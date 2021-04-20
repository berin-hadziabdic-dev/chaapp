package com.profile_messages.profile_messages.service;

import java.util.Optional;

import com.profile_messages.profile_messages.dto.ProfileDto;
import com.profile_messages.profile_messages.entities.Profile;
import com.profile_messages.profile_messages.exceptions.BadRequestException;
import com.profile_messages.profile_messages.repositories.ProfileRepository;

import org.springframework.stereotype.Service;

import lombok.NoArgsConstructor;

@Service
public class ProfileService {
    
    private ProfileRepository profileRepo;


    public ProfileService(ProfileRepository repository)
    {
        this.profileRepo = repository;
    }


    public Profile findProfile(String username)
    {
        Profile returnProfile = null;
        Optional<Profile> userProfile = this.profileRepo.findById(username);

        if(userProfile.isPresent())
           returnProfile = userProfile.get();
        else 
            throw new BadRequestException("Requested profile for a username that does not exist.");

        return returnProfile;
    }

    public ProfileDto getUserProfile(String username) throws BadRequestException
    {
        ProfileDto returnProfile = new ProfileDto(findProfile(username));
       
        return returnProfile;
    }

   /**Updating phonenumbers and emails still need to be designed since 
    * those partiuclar updates depends on the emailer and sms microservices.. */

   public void deleteUserProfile(String username)
   {
       Profile deleteProfile = this.findProfile(username);
       this.profileRepo.delete(deleteProfile);
   }

   public void createProfile(String username, ProfileDto dto)
   {
       boolean badRequestExceptionThrown = false;
    
       try 
       {
        Profile profileToCreate = new Profile(dto);
        this.profileRepo.save(profileToCreate);
       }
       catch(Exception e)
       {
            throw new BadRequestException(e.toString()); // a problem creating the profile throw an exception compatiable with @ControllerAdvice.
       }

    
   }

}
