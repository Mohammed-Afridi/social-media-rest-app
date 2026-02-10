package com.projects.socialmedia.social_media;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message); // passes exception to RuntimeException
    }
}


// Notes
// Here we change throwable  to extends RuntimeException because we dont want checked exception