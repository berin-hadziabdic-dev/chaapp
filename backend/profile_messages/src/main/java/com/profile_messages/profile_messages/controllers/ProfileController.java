package com.profile_messages.profile_messages.controllers;

import java.sql.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.profile_messages.profile_messages.dto.ProfileDto;
import com.profile_messages.profile_messages.service.ProfileService;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    ProfileService profileService;

    public ProfileController(ProfileService service)
    {
        this.profileService = service;
    }

    @GetMapping(path="/{username}")
    public ResponseEntity<ProfileDto> getProfile(@NotNull @NotBlank @PathVariable String username)
    {
        ProfileDto returnDto = this.profileService.getUserProfile(username);

        return ResponseEntity.ok(returnDto);
    }

}
