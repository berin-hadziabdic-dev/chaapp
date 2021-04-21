package com.profile_messages.profile_messages.service;

import java.util.Optional;

import com.profile_messages.profile_messages.dto.MessageDto;
import com.profile_messages.profile_messages.entities.Chat;
import com.profile_messages.profile_messages.entities.Message;
import com.profile_messages.profile_messages.exceptions.BadRequestException;
import com.profile_messages.profile_messages.exceptions.Http403Exception;
import com.profile_messages.profile_messages.matchers.StringMatcher;
import com.profile_messages.profile_messages.repositories.MessageRepository;

import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private ChatService chatService;
    private StringMatcher matcher;
    private MessageRepository messageRepo;



    public MessageService(StringMatcher matcher, ChatService service,MessageRepository messageRepo)
    {
        this.matcher = matcher;
        this.chatService = service;
        this.messageRepo = messageRepo;
    }

    public void addMessageToNewChat(Chat newlyCreatedChat,String username, MessageDto newChat)
    {
        this.chatService.findChatForUser(username, newlyCreatedChat.getChat_id());// ensure user can access chat
        Message newlyCreatedMessage = new Message(newChat);
        this.messageRepo.save(newlyCreatedMessage);
    }

    /**Add message adds a message to a chat only if the username parameter is in 
     * the list of allowed participants.
    */
    public void addMessageToExistingChat(String username,MessageDto message) throws Http403Exception
    {
        Integer chat_id = message.getChat_id();
        String sender_username = message.getSender_username();
        
        Message new_message = new Message(message);
        this.messageRepo.save(new_message);
    
    }

    public void updateMessage(String username, MessageDto dto)
    {
        String newMessage = null;
        Message messageToUpdate = this.findMessageById(dto.getId()); //attempts ot find msg will throw exception if not found.
        checkIfUserOwnsMessage(username, messageToUpdate);    //if user does not own message, throw exception    
        
        newMessage = dto.getMessage() != null ? dto.getMessage() : "";
        messageToUpdate.setMessage(dto.getMessage());
        this.messageRepo.save(messageToUpdate);
    }

    public void deleteMessage(String username, Integer message_id)
    {
        Message discoveredMessage = this.findMessageById(message_id); //find msg
        checkIfUserOwnsMessage(username, discoveredMessage); //if user owns message they can delete it

        this.messageRepo.delete(discoveredMessage); //delete message.
        
    }

    private void checkIfUserOwnsMessage(String username,Message message)
    {
        if(!message.getSender_username().equals(username))
            throw new Http403Exception("The user attempted to access a message they are not the sender of.");
    }

    private Message findMessageById(Integer message_id)
    {
        Optional<Message> discoveredMessage = this.messageRepo.findById(message_id);
        Message returnMessage = null;

        if(!discoveredMessage.isPresent()) //if msg does not exist, user requesting a non existing msg.
            throw new BadRequestException("The user requested a message id that did not exist.");

        returnMessage = discoveredMessage.get();



        return returnMessage;
    }
    
}
