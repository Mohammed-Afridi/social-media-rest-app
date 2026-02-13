package com.projects.socialmedia.social_media.exception;

// Default excepiton handler is ResponseEntityExceptionHandler by springboot ,
// we need to extend and override that to customize

import com.projects.socialmedia.social_media.UserNotFoundException;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;


@ControllerAdvice // Lets make this applicable to all the controller present in specific project
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class) // Here we say handle all exceptions...
    public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) throws Exception {

        // copy method handleException from parent class and rename it to handleAllExceptions

        ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(),ex.getMessage(),request.getDescription(false));

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);

        // Now go and check the exception in API Talent tester
    }

    // Now I want a different error response for user not found ( BAD REQUEST )

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleUserNotFoundExceptions(Exception ex, WebRequest request) throws Exception {

        ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(),ex.getMessage(),request.getDescription(false));

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        // Here we are actually overriding , not like above.. anove was just creating a new method
        // Now copy the above lines from the method to customize

        ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(),"Total Errors :" + ex.getErrorCount() + ex.getFieldError().getDefaultMessage(),request.getDescription(false));

        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);

        //After writing this method , we get message , but its still a long message. so we have to customize in User.java

    }
}
