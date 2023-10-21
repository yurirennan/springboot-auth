package com.yuri.projects.auth.exceptions;

import lombok.Data;

@Data
public class UserNotAuthorizedException extends RuntimeException {

    public UserNotAuthorizedException(final String message) {
        super(message);
    }

}
