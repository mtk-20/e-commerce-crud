package com.example.e_commerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNameAlreadyExistException.class)
    public ResponseEntity<String> userNameAlreadyExist(UserNameAlreadyExistException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<String> emailAlreadyExist(EmailAlreadyExistException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNameNotFoundException.class)
    public ResponseEntity<String> userNameNotFound(UserNameNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserIdNotFoundException.class)
    public ResponseEntity<String> userIdNotFound(UserIdNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserIdAlreadyExistException.class)
    public ResponseEntity<String> userIdAlreadyExist(UserIdAlreadyExistException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotEnoughStockException.class)
    public ResponseEntity<String> notEnoughStock(NotEnoughStockException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INSUFFICIENT_STORAGE);
    }

}
