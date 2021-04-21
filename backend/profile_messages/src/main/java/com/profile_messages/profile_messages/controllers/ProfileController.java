package com.profile_messages.profile_messages.controllers;

import java.sql.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.profile_messages.profile_messages.dto.ProfileDto;
import com.profile_messages.profile_messages.service.ProfileService;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/profile")
@AllArgsConstructor
public class ProfileController {

    ProfileService profileService;



    @GetMapping(path="/{username}")
    public ResponseEntity<ProfileDto> getProfile(@NotNull @NotBlank @PathVariable String username)
    {
        ProfileDto returnDto = this.profileService.getUserProfile(username);

        return ResponseEntity.ok(returnDto);
    }

    /** /profile/{username} is used to create a new profile.
     * @param session_username the username obtained from an authenticated session.
     * @param dto the dto which describes the profile to create
     */
    @PostMapping(path="/{username}")
    @ResponseStatus(HttpStatus.OK)
    public void createProfile(@Valid @NotNull @NotBlank @PathVariable("username") String session_username,@Valid @NotNull @RequestBody ProfileDto dto)
    {
        this.profileService.createProfile(session_username, dto);
    }

}
