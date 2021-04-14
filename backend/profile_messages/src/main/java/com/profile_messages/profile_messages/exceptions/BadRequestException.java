package com.profile_messages.profile_messages.exceptions;

public class BadRequestException  extends RuntimeException{
    
    public BadRequestException() 
    {
        super("The user requested data that did not exist, or requested non existing data.");
    }

    public BadRequestException(String string) {
        super(string);
    }
}
