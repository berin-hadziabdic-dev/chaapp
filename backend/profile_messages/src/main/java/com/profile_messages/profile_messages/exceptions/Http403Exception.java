package com.profile_messages.profile_messages.exceptions;

public class Http403Exception extends RuntimeException{

    public Http403Exception() 
    {
        super("An authenticated user attempted to access a resource they are not allowed to access.");
    }

    public Http403Exception(String string) {
        super(string);
    }
    
}
