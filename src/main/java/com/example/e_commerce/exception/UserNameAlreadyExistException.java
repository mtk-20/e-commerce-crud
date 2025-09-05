package com.example.e_commerce.exception;

public class UserNameAlreadyExistException extends RuntimeException {

    public UserNameAlreadyExistException(String message) {
        super(message);
    }
}
