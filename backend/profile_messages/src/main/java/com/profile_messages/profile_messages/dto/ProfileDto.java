package com.profile_messages.profile_messages.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.profile_messages.profile_messages.entities.Profile;

import org.springframework.validation.annotation.Validated;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
@EqualsAndHashCode
public class ProfileDto {

    private String username;
    private String email;
    private Integer area_code;
    private Integer phone_number;

   

    public ProfileDto(Profile profile)
    {
        this.username = profile.getUsername();
        this.email = profile.getEmail();
        this.area_code = profile.getArea_code();
        this.phone_number = profile.getPhone_number();

    }
}
