package com.example.GroupProject_4.Controllers;

import com.example.GroupProject_4.Models.PostModel;
import com.example.GroupProject_4.Request.PostRequest.CreatePostRequest;
import com.example.GroupProject_4.Request.PostRequest.DeletePostRequest;
import com.example.GroupProject_4.Request.PostRequest.PostEditRequest;
import com.example.GroupProject_4.Response.PostResponse.CreatePostResponse;
import com.example.GroupProject_4.Services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostService postService;


    @PostMapping("/CreatePost")
    public CreatePostResponse createPost(@RequestBody @Valid CreatePostRequest createPostRequest)
    {
        return postService.createPost(createPostRequest);
    }

    @DeleteMapping("/RemovePost")
    public void removePost(@RequestBody DeletePostRequest deletePostRequest)
    {
        postService.deletePost(deletePostRequest);
    }

    @PutMapping("/EditPost")
    public CreatePostResponse editPost(@RequestBody PostEditRequest postEditRequest)
    {
        return postService.edit(postEditRequest);
    }

    @GetMapping("/GetPosts")
    public List<CreatePostResponse> getAllPost(@RequestParam int pageNumber)
    {
        return postService.getAllPost(pageNumber);
    }


    @GetMapping("/getById/{id}")
    public CreatePostResponse getPostById(@RequestParam("id") String id)
    {
        return postService.getOnePost(id);
    }



    @GetMapping("/GetUserPosts")
    public List<CreatePostResponse> GetUserPosts(String username, int pageNumber)
    {
        return postService.getUserPosts(username,pageNumber);
    }



}
