package com.example.GroupProject_4.Request.PostRequest;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

public record CreatePostRequest(
        //@NotNull(message = "username can'be null")
        //String username,
        @NotNull(message = "text can't be null")
        @Size(min = 2,max = 512,message = "text can'be lower 2 and higher 512")
        String text) {}
