package com.example.GroupProject_4.Request.CommentRequest;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


public record WriteCommentRequest(String username,String postid,String text){} /*{
    private String username;
    private UUID postid;
    private String text;
}*/
