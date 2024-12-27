package com.example.GroupProject_4.Admin.Request;

public record AdminDeleteCommentRequest(
        String username,
        String commentId,
        String postId
) {}
