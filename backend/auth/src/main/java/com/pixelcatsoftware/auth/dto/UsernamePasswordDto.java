package com.pixelcatsoftware.auth.dto;

import lombok.Data;

/**UsernamePasswordDto is used to read in user credentials from an HttpRequest. */
@Data
public class UsernamePasswordDto {
    private String username;
    private String password;

    /** Allows the Dto to do shallow validation on itself.
     * @return true if valid dto, or false if not.
     */
    public boolean isInvalid(){
        return username == null || password == null || username.equals("") || password.equals("");
    }
}
