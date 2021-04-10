package com.pixelcatsoftware.auth.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration("auth-config")
public class AuthBeans {

    @Bean
    public PasswordEncoder getEncoder(){
        return  NoOpPasswordEncoder.getInstance();
    }

    @Bean 
    public ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }

}