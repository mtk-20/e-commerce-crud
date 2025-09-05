package com.example.e_commerce.exception;

public class EmailAlreadyExistException extends RuntimeException{

    public EmailAlreadyExistException(String message) {
        super(message);
    }
}
