package com.pixelcatsoftware.auth.controller;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping( produces = "application/json")
    public ResponseEntity sessionTest(){
        return ResponseEntity.ok().build();
    }
    @PostMapping(path="/login",produces = "application/json")
    public String loginTest(){
        return "You logged in";
    }

    /**When a user succesfully logsout via the Logout filter, a 200 code is sent back to the app. */
    @PostMapping(path="/logout")
    @ResponseStatus(HttpStatus.OK)
    public void logoutSuccess(@Validated @NotNull HttpSession userSession){
        userSession.invalidate();
    }
    

 
}
