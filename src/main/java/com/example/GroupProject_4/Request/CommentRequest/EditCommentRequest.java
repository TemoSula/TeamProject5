package com.example.GroupProject_4.Request.CommentRequest;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


public record EditCommentRequest(String username,String postid,String text,String commentId) {
    /*public String username;
    public String postid;
    public String text;*/
}
