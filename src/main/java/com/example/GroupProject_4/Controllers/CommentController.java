package com.example.GroupProject_4.Controllers;

import com.example.GroupProject_4.Models.CommentModel;
import com.example.GroupProject_4.Request.CommentRequest.DeleteCommentRequest;
import com.example.GroupProject_4.Request.CommentRequest.EditCommentRequest;
import com.example.GroupProject_4.Request.CommentRequest.WriteCommentRequest;
import com.example.GroupProject_4.Request.PostRequest.DeletePostRequest;
import com.example.GroupProject_4.Response.CommentResponse;
import com.example.GroupProject_4.Services.CommentService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@EnableMethodSecurity
public class CommentController {

    @Autowired
    CommentService comntService;


    @PostMapping("/WriteComment")
    public CommentResponse writeComment(@RequestBody @Valid WriteCommentRequest writeCommentRequest)
    {
        return comntService.writeComment(writeCommentRequest);
    }

    @DeleteMapping("/RemoveComment")
    public void deleteComment(@RequestBody DeleteCommentRequest deleteCommentRequest)
    {
        comntService.deleteComment(deleteCommentRequest);
    }

    @PutMapping("/EditComment")
     public CommentResponse editComment(@RequestBody EditCommentRequest editCommentRequest)
    {
        return comntService.editComment(editCommentRequest);
    }

}
