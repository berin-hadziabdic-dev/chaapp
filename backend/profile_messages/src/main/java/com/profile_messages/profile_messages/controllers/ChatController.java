package com.profile_messages.profile_messages.controllers;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.google.inject.spi.Message;
import com.profile_messages.profile_messages.dto.ChatDto;
import com.profile_messages.profile_messages.dto.MessageDto;
import com.profile_messages.profile_messages.entities.Chat;
import com.profile_messages.profile_messages.exceptions.BadRequestException;
import com.profile_messages.profile_messages.exceptions.Http403Exception;
import com.profile_messages.profile_messages.matchers.StringMatcher;
import com.profile_messages.profile_messages.service.AllowedChatterService;
import com.profile_messages.profile_messages.service.ChatService;
import com.profile_messages.profile_messages.service.MessageService;
import com.profile_messages.profile_messages.service.ProfileService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@RestController
@RequestMapping("/chat")
@AllArgsConstructor
public class ChatController {

    StringMatcher matcher;

    ChatService chatService;
    MessageService messageService;
    AllowedChatterService allowedChatterService;
    ProfileService profileService;

    /** \/chat/username/chat_id is used to get chatdata for a user.*/
    @GetMapping(path="/{username}/{chat_id}")
    public ResponseEntity<ChatDto> getChatForUser(@Valid @NotNull @NotBlank @PathVariable String username,@Valid @NotNull @NotBlank @PathVariable Integer chat_id)
    {
        Chat discoveredChat = this.chatService.findChatForUser(username, chat_id);
        ChatDto returnData = new ChatDto(discoveredChat);

        return ResponseEntity.ok(returnData);
    }

    /** \/chat/username/chat_id is used to send a message for a user in chat.*/
    @PostMapping(path="/{username}")
    @ResponseStatus(HttpStatus.OK)
    public void sendMessage(@Valid @NonNull @NotBlank @PathVariable String username ,@Valid @NonNull @NotBlank @RequestBody MessageDto dto)
    {
        this.validateUsername(username, dto.getSender_username()); //ensure dto and session username match
        this.chatService.userCanSendMessagesInChat(username,dto.getChat_id()); //ensure user can send messeges in said chat.
        this.messageService.addMessageToExistingChat(username,dto); // add the message to the chat.
    }

    /**This api endpoint attempts to create a new chat between two users. */
    @PostMapping(path="/new/{sender}/{reciever}")
    @ResponseStatus(HttpStatus.OK)
    public void createNewAndSendMessage(@Valid @NotBlank @NotNull @PathVariable String sender, @Valid @NotBlank @NotNull @PathVariable String reciever,MessageDto dto)
    {
        validateUsername(sender, dto.getSender_username()); //ensure the sender is sending a message on the behalf of themselves.
        this.profileService.findProfile(sender);
        this.profileService.findProfile(reciever);

        Chat createdChat = this.chatService.create(); // if sender or reciever do not exist, an err msg will be thrown.

        this.allowedChatterService.addChatterToChat(createdChat, sender); //add sender to chat
        this.allowedChatterService.addChatterToChat(createdChat, reciever); // add reciever to chat
        this.messageService.addMessageToExistingChat(sender, dto); //send message created with new chat.
    }

    private void validateUsername(@Valid @NotNull @NotBlank String sessionUsername, @Valid @NotNull @NotBlank String usernameToValidate)
    {
        if(!this.matcher.match(sessionUsername, usernameToValidate))
            throw new Http403Exception("The username owned a Session username that did not correspond to the username they passed in a DTO.");
    
    }

    
    
}
