package com.profile_messages.profile_messages.dto;

import javax.validation.constraints.NotNull;
import com.profile_messages.profile_messages.entities.ContactDetails;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContactDetailsDto {
    @NotNull
    private ProfileDto contactDetails;

    public ContactDetailsDto(ContactDetails details)
    {
        this.contactDetails = new ProfileDto(details.getConnectedUser());
    }
}
