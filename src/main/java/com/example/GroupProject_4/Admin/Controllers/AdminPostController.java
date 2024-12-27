package com.example.GroupProject_4.Admin.Controllers;

import com.example.GroupProject_4.Admin.Request.AdminDeletePostRequest;
import com.example.GroupProject_4.Admin.Request.AdminEditPostRequest;
import com.example.GroupProject_4.Admin.Services.AdminPostService;
import com.example.GroupProject_4.Response.PostResponse.CreatePostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Admin")
@EnableMethodSecurity
public class AdminPostController {

    @Autowired
    AdminPostService postService;
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/DeletePost")
    public void deletePost(@RequestBody AdminDeletePostRequest deletePost)
    {
        postService.deletePost(deletePost);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/EditPost")
    public CreatePostResponse edit(@RequestBody AdminEditPostRequest editRequest)
    {
        return postService.edit(editRequest);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/GetOnePost")
    public CreatePostResponse getOnePost(@RequestParam String postId)
    {
        return postService.getOnePost(postId);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getUserPosts")
    public List<CreatePostResponse> getUserPost(String username, int pageNumber) //10 element per page
    {
        return postService.getUserPosts(username,pageNumber);
    }

}
