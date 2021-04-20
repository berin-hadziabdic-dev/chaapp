package com.profile_messages.profile_messages.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.profile_messages.profile_messages.entities.AllowedChatter;
import com.profile_messages.profile_messages.entities.Chat;
import com.profile_messages.profile_messages.entities.Message;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**The ChatDto object carries chat related entity information from the 
 * front end to the back end.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class ChatDto {
    private Integer chat_id;
    private List<MessageDto> chatMessages;
    private List<AllowedParticipantDto> participants;
    
    public ChatDto(Chat chat)
    {
        this.chat_id= chat.getChat_id();
        this.chatMessages = new ArrayList<>();
        this.participants = new ArrayList<>();

        if(chat.getChatMessages() != null && chat.getChatMessages().size() > 0)
            for(Message chatMessage: chat.getChatMessages())
            {
                this.chatMessages.add(new MessageDto(chatMessage));
            }
        if(chat.getAllowedParticipants().size() > 0)
            for(AllowedChatter chatter : chat.getAllowedParticipants())
                 this.participants.add(new AllowedParticipantDto(chatter));

        
    }
}
