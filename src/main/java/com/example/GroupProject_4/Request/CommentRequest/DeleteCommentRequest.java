package com.example.GroupProject_4.Request.CommentRequest;

import lombok.Getter;
import lombok.Setter;


public record DeleteCommentRequest(/*String username,*/String postid,String commentId) {
}
