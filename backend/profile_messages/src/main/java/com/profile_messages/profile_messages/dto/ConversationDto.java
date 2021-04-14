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
   
    private String user_one;
    private String user_two;

    public ConversationDto(Conversation conversation)
    {
        this.user_one = conversation.getUser_one();
        this.user_two = conversation.getUser_two();
        this.messages = new ArrayList<MessageDto>();

        for(Message message : conversation.getMessages())
            this.messages.add(new MessageDto(message));
    }

    
}
