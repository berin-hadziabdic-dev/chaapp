package com.pixelcatsoftware.auth.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    

 
}
