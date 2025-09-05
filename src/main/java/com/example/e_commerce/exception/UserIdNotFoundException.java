package com.example.e_commerce.exception;

public class UserIdNotFoundException extends RuntimeException {

    public UserIdNotFoundException(String message) {
        super(message);
    }
}
