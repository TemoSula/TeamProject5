package com.example.GroupProject_4.Admin.Controllers;

import com.example.GroupProject_4.Admin.Request.AdminDeleteCommentRequest;
import com.example.GroupProject_4.Admin.Request.AdminEditCommentRequest;
import com.example.GroupProject_4.Admin.Services.AdminCommentService;
import com.example.GroupProject_4.Response.CommentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Admin")
@EnableMethodSecurity
public class AdminCommentController {

    @Autowired
    AdminCommentService commentService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/DeleteComment")
    public void deleteComment(@RequestBody AdminDeleteCommentRequest deleteCommentRequest)
    {
        commentService.deleteComment(deleteCommentRequest);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/EdicComment")
    public CommentResponse editComment(@RequestBody AdminEditCommentRequest editRequest){
        return commentService.editComment(editRequest);
    }


}
