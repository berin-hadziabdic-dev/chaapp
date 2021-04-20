package com.profile_messages.profile_messages.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.profile_messages.profile_messages.entities.Profile;
import lombok.Data;

@Data
public class ProfileDto {

    @NotNull @NotBlank
    private String username;
    @NotNull @NotBlank
    private String email;
    @NotNull
    private Integer area_code;
    @NotNull
    private Integer phone_number;

   

    public ProfileDto(Profile profile)
    {
        this.username = profile.getUsername();
        this.email = profile.getEmail();
        this.area_code = profile.getArea_code();
        this.phone_number = profile.getPhone_number();

    }
}
