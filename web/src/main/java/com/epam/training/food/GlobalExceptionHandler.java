package com.epam.training.food;

import com.epam.training.food.service.LowBalanceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LowBalanceException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<String> handleLowBalanceException(LowBalanceException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
    }
}