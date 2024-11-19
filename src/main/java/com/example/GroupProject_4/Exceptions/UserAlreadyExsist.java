package com.example.GroupProject_4.Exceptions;

public class UserAlreadyExsist extends RuntimeException{
    public UserAlreadyExsist(String message) {
        super(message);
    }
}
