package com.example.GroupProject_4.Admin.Request;

public record AdminEditCommentRequest(String username, String commentId, String postId, String text) {
}
