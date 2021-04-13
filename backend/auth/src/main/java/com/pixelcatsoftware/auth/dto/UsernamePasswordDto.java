package com.pixelcatsoftware.auth.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import lombok.Data;

/**UsernamePasswordDto is used to read in user credentials from an HttpRequest. */
@Data
@Validated
public class UsernamePasswordDto {
    @NotNull @NotBlank
    private String username;
    @NotNull @NotBlank
    private String password;
}
