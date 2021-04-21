package com.profile_messages.profile_messages;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.discovery.converters.Auto;
import com.profile_messages.profile_messages.controllers.ProfileController;
import com.profile_messages.profile_messages.dto.ProfileDto;

import org.apache.catalina.core.ApplicationContext;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.hamcrest.Matchers.*;

/**This test class tests the Profile portion of the api. */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "local-dev")
class ProfileMessagesApplicationTests {

    @Autowired
    MockMvc mock_server;
    @Autowired
    ObjectMapper reader;
    
    ProfileDto testDto;


    @BeforeEach
    public void setUp()
    {
        testDto = new ProfileDto();
        testDto.setUsername("test_user");
        testDto.setEmail("test_user@test.com");
        testDto.setPhone_number(7777777);
        testDto.setArea_code(555);
    }

  

    
    @Test
    public void testGettingValidProfile() throws Exception
    {
           this.mock_server
            .perform(get("/profile/test"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.username",is("test")));
    }


    @Test
    public void testGettingInvalidProfile() throws Exception
    {
        this.mock_server
            .perform(get("/profile/dne"))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(""));

    }

    @Test
    public void testCreatingDuplicateUsernameProfile() throws Exception
    {
        
        testDto.setUsername("test_user");
        testDto.setEmail("test_1user@test.com");
        testDto.setPhone_number(6777777);
        testDto.setArea_code(455);
        

        this.mock_server
            .perform(post("/profile/" + testDto.getUsername(),testDto))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(""));
    }

    @Test
    public void testCreatingDuplicateUsernameEmail() throws Exception
    {
        
        testDto.setUsername("test1_user");
        testDto.setEmail("test_user@test.com");
        testDto.setPhone_number(6777777);
        testDto.setArea_code(455);
        

        this.mock_server
            .perform(post("/profile/" + testDto.getUsername(),testDto))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(""));
    }

    @Test
    public void testCreatingNewProfile() throws Exception
    {
        
        testDto.setUsername("new_test_user");
        testDto.setEmail("new_test_user@test.com");
        testDto.setPhone_number(9977777);
        testDto.setArea_code(333);
        

        this.mock_server
            .perform(post("/profile/" + testDto.getUsername())
            .content(reader.writeValueAsString(testDto)).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(""));
    }


    @Test
    public void testCreatingNewProfileWithMisMatchingUsername() throws Exception
            {
        
            testDto.setUsername("new_test_user");
            testDto.setEmail("new_test_user@test.com");
            testDto.setPhone_number(9977777);
            testDto.setArea_code(333);
            
    
            this.mock_server
                .perform(post("/profile/" + "not_matching_user")
                .content(reader.writeValueAsString(testDto)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(""));
        }   
        


}
