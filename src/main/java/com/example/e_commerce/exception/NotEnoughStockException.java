package com.example.e_commerce.exception;

public class NotEnoughStockException extends RuntimeException{
    public NotEnoughStockException(String message) {
        super(message);
    }
}
