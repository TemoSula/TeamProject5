package com.example.GroupProject_4.Controllers;

import com.example.GroupProject_4.Models.PostModel;
import com.example.GroupProject_4.Request.PostRequest.CreatePostRequest;
import com.example.GroupProject_4.Request.PostRequest.DeletePostRequest;
import com.example.GroupProject_4.Request.PostRequest.PostEditRequest;
import com.example.GroupProject_4.Response.PostResponse.CreatePostResponse;
import com.example.GroupProject_4.Services.PostService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableMethodSecurity
public class PostController {

    @Autowired
    PostService postService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/CreatePost")
    public CreatePostResponse createPost(@RequestBody @Valid CreatePostRequest createPostRequest)
    {
        return postService.createPost(createPostRequest);
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/RemovePost")
    public void removePost(@RequestBody DeletePostRequest deletePostRequest)
    {
        postService.deletePost(deletePostRequest);
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/EditPost")
    public CreatePostResponse editPost(@RequestBody @Valid PostEditRequest postEditRequest)
    {
        return postService.edit(postEditRequest);
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/GetPosts")
    public List<CreatePostResponse> getAllPost(@RequestParam int pageNumber)
    {
        return postService.getAllPost(pageNumber);
    }


    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN')")
    @GetMapping("/GetUserPosts")
    public List<CreatePostResponse> GetUserPosts(/*String username,*/ int pageNumber)
    {
        return postService.getUserPosts(/*username,*/pageNumber);
    }



}
