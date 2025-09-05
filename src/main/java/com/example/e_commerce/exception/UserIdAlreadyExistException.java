package com.example.e_commerce.exception;

public class UserIdAlreadyExistException extends RuntimeException{
    public UserIdAlreadyExistException(String message) {
        super(message);
    }
}
