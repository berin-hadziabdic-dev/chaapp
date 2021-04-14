package com.profile_messages.profile_messages.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.profile_messages.profile_messages.entities.ConversationRecord;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConversationRecordDto {
   
    @NotNull @NotBlank
    private String owning_username;
    @NotNull
    private ConversationDto storedConversation;

    public ConversationRecordDto(ConversationRecord record)
    {
        this.owning_username = record.getOwning_username();
        this.storedConversation =new ConversationDto(record.getStoredConversation());
    }


}
