package com.yuri.projects.auth.handlers;

import com.yuri.projects.auth.exceptions.UserNotAuthorizedException;
import com.yuri.projects.auth.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(UserNotAuthorizedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiErrors handleUserNotAuthorizedException(final UserNotAuthorizedException ex) {
        return new ApiErrors(ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handleUserNotFoundException(final UserNotFoundException ex) {
        return new ApiErrors(ex.getMessage());
    }

}
