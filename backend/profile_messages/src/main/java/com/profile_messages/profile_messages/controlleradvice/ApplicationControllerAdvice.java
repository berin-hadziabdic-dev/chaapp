package com.profile_messages.profile_messages.controlleradvice;

import com.profile_messages.profile_messages.exceptions.BadRequestException;
import com.profile_messages.profile_messages.exceptions.Http403Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApplicationControllerAdvice {
    
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void badRequestResponse(){}

    @ExceptionHandler(Http403Exception.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void forbiddenResponse(){}
}
