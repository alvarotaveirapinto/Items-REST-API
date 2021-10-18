package com.example.bootcamp.exception;

public class ItemNotAvailableException extends RuntimeException {

   private String message;

    public ItemNotAvailableException() {}

    public ItemNotAvailableException(String message) {
        super(message);
        this.message = message;
    }
}
