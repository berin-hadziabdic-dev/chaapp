package com.profile_messages.profile_messages.service;

import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.profile_messages.profile_messages.dto.AllowedParticipantDto;
import com.profile_messages.profile_messages.dto.ChatDto;
import com.profile_messages.profile_messages.entities.AllowedChatter;
import com.profile_messages.profile_messages.entities.Chat;
import com.profile_messages.profile_messages.entities.Profile;
import com.profile_messages.profile_messages.exceptions.BadRequestException;
import com.profile_messages.profile_messages.exceptions.Http403Exception;
import com.profile_messages.profile_messages.matchers.StringMatcher;
import com.profile_messages.profile_messages.repositories.AllowedChatterRepository;
import com.profile_messages.profile_messages.repositories.ChatRepository;
import com.profile_messages.profile_messages.repositories.ProfileRepository;

import org.springframework.stereotype.Service;


/**The CharService class provides Services for Chats involving 
 * TWO users only. It also leverges the message service to add new messages.
 */
@Service
public class ChatService {
    
    private ChatRepository chatRepo;
    private AllowedChatterRepository allowedParticipants;
    private ProfileService profileService;
    private StringMatcher matcher;
    

    public ChatService(ChatRepository chatRepo,StringMatcher matcher)
    {
        this.chatRepo = chatRepo;
        this.matcher = matcher;
    }

    

    /**The create function creates a new chat and persists it to the datastore.  */
    public Chat create()
    {
      Chat newChat = new Chat();
      newChat = this.chatRepo.save(newChat); //create the chat

      return newChat;
    }
    
    public void delete(Integer chatId) 
    {
        Chat discoveredChat = this.findChat(chatId);
        this.chatRepo.delete(discoveredChat);
    }


    public Chat findChat(Integer chatId)
    {
        Optional<Chat> discoveredChat = this.chatRepo.findById(chatId);
        Chat returnChat = null;
        if(discoveredChat.isPresent())
            returnChat = discoveredChat.get();
        else 
            throw new BadRequestException("Attempted to locate a chat which does not exist.");
    
        return returnChat;
    }

	public Chat findChatForUser(String username, Integer chat_id) throws Http403Exception {

        Chat discoveredChat = this.findChat(chat_id);
      
        this.userCanSendMessagesInChat(username, chat_id); //ensure user can send chats, if not throw error.
        
        return discoveredChat;
    }
    

    public boolean userCanSendMessagesInChat(String username, Integer chat_id)
    {
        Chat discoveredChat = this.findChat(chat_id);
        boolean userCanAccessChat = false;

        //go thrrough allowed participants and if a matching username is not found throw an exception which will return a 400
        for(AllowedChatter chatter: discoveredChat.getAllowedParticipants())
            if(this.matcher.match(username, chatter.getOwning_username()))
            {
                userCanAccessChat = true;
                break;
            }

        if(!userCanAccessChat)
            throw new Http403Exception("User attempted to access chat they are not involved in.");

        return userCanAccessChat;

    }

}
