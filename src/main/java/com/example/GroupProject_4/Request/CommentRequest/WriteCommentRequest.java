package com.example.GroupProject_4.Request.CommentRequest;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


public record WriteCommentRequest(

        @NotNull(message = "username can't be null")
        String username,
        @NotNull(message = "postid can't be null")
        String postid,
        @NotNull(message = "text can't be null")
        @Size(min = 2,max = 128,message = "text can'be lower 2 and higher 512")
        String text){}

