package com.profile_messages.profile_messages.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.profile_messages.profile_messages.entities.Conversation;
import com.profile_messages.profile_messages.entities.Message;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ConversationDto {
   
    @NotNull @Min(0)
    private List<MessageDto> messages;
   
    public ConversationDto(Conversation conversation)
    {
        this.messages = new ArrayList<MessageDto>();

        for(Message message : conversation.getMessages())
            this.messages.add(new MessageDto(message));
    }

    
}
