package com.example.GroupProject_4.Exceptions;

import org.hibernate.dialect.unique.CreateTableUniqueDelegate;

public class UserNotFound extends RuntimeException{

    public UserNotFound(String message) {
        super(message);
    }
}
