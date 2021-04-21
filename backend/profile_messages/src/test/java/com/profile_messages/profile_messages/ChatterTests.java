package com.profile_messages.profile_messages;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.spi.Message;
import com.profile_messages.profile_messages.dto.MessageDto;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import javax.print.attribute.standard.Media;

import static org.hamcrest.Matchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "local-dev")
public class ChatterTests {
    @Autowired
    MockMvc mock_server;
    @Autowired
    ObjectMapper mapper;

   
    @Test
    public void sendMessageInExistingChat() throws Exception
    {
        MessageDto newMessage = new MessageDto();
        newMessage.setChat_id(1);
        newMessage.setMessage("message sent through test");
        newMessage.setSender_username("test");
        newMessage.setId(99);
        
        mock_server.
            perform(post("/chat/test").content(mapper.writeValueAsString(newMessage)).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(""));
    }


    @Test
    public void sendMessageInExistingChatWithNonExistingUser() throws Exception
    {
        MessageDto newMessage = new MessageDto();
        newMessage.setChat_id(1);
        newMessage.setMessage("message sent through test");
        newMessage.setSender_username("test_dne");
        newMessage.setId(99);
        
        mock_server.
            perform(post("/chat/test_dne").content(mapper.writeValueAsString(newMessage)).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(""));
    }

    @Test
    public void sendMessageInExistingButNotAllowedChat() throws Exception
    {
        MessageDto newMessage = new MessageDto();
        newMessage.setChat_id(0);
        newMessage.setMessage("message sent through test");
        newMessage.setSender_username("test3");
        newMessage.setId(100);
        
        mock_server.
            perform(post("/chat/test3").content(mapper.writeValueAsString(newMessage)).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isForbidden())
            .andExpect(content().string(""));
    }

    @Test
    public void sendMessageInNonExistingChat() throws Exception
    {
        MessageDto newMessage = new MessageDto();
        newMessage.setChat_id(9999);
        newMessage.setMessage("message sent through test");
        newMessage.setSender_username("test1");
        newMessage.setId(100);
        
        mock_server.
            perform(post("/chat/test1").content(mapper.writeValueAsString(newMessage)).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(""));
    }

    @Test
    public void createNewChatAndSendMessageInNewChat() throws Exception
    {
        MessageDto msg = new MessageDto();
        msg.setChat_id(null);
        msg.setId(101);
        msg.setSender_username("test2");
        msg.setMessage("new chat message");

        MessageDto secondMsg = new MessageDto();
    

        mock_server
            .perform(post("/chat/new/test2/test3").content(mapper.writeValueAsString(msg)))
            .andExpect(status().isOk());
    
    }

    
}
