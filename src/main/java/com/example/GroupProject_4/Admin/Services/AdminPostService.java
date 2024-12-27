package com.example.GroupProject_4.Admin.Services;

import com.example.GroupProject_4.Admin.Request.AdminDeletePostRequest;
import com.example.GroupProject_4.Admin.Request.AdminEditPostRequest;
import com.example.GroupProject_4.Exceptions.PostNotFound;
import com.example.GroupProject_4.Exceptions.UserNotFound;
import com.example.GroupProject_4.Models.PostModel;
import com.example.GroupProject_4.Models.UserModel;
import com.example.GroupProject_4.Repositories.CommentRepository;
import com.example.GroupProject_4.Repositories.PostRepository;
import com.example.GroupProject_4.Repositories.UserRepostory;
import com.example.GroupProject_4.Request.PostRequest.CreatePostRequest;
import com.example.GroupProject_4.Request.PostRequest.DeletePostRequest;
import com.example.GroupProject_4.Request.PostRequest.PostEditRequest;
import com.example.GroupProject_4.Response.PostResponse.CreatePostResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AdminPostService {

    @Autowired
    PostRepository postRepo;

    @Autowired
    UserRepostory userRepo;

    @Autowired
    CommentRepository commentRepository;

    @Transactional
    public void deletePostAndComments(String postId) {
        commentRepository.removeByPostId(postId);

        postRepo.deleteById(postId);
    }


    public void deletePost(AdminDeletePostRequest deletePostRequest)
    {
        UserModel userModel = userRepo.findByUsername(deletePostRequest.username());
        if(userModel == null)
        {
            throw new UserNotFound("user does not exsist");
        }
        PostModel postModel = postRepo.getUsernameAndPostId(deletePostRequest.postId(),userModel.getId());

        if(postModel == null)
        {
            throw new PostNotFound("this user doest not have this post");
        }

        deletePostAndComments(deletePostRequest.postId());
        //postRepo.delete(postModel);


    }


    public CreatePostResponse edit(AdminEditPostRequest postEditRequest)
    {
        UserModel userModel = userRepo.findByUsername(postEditRequest.username());
        if(userModel == null)
        {
            throw new UserNotFound("user does not exsist");
        }
        PostModel postModel = postRepo.getUsernameAndPostId(postEditRequest.postId(),userModel.getId());
        if(postModel == null)
        {
            throw new PostNotFound("this user doest not have this post");
        }

        postModel.setText(postEditRequest.text());
        postRepo.save(postModel);
        return new CreatePostResponse(postModel.getText(),userModel.getUserName(),postModel.getId());

    }

    public List<CreatePostResponse> getAllPost(int pageNumber)
    {
        Pageable pageable = PageRequest.of(pageNumber,10);
        Page<PostModel> pagePostModel = postRepo.getAllPost(pageable);

        //List<CreatePostResponse> createPostResponses = new ArrayList<>();

        List<CreatePostResponse> cpr = pagePostModel.toList().stream().map
                (c -> new CreatePostResponse(c.getText(), c.getUsermodel().getUserName(),c.getId())).collect(Collectors.toList());
        /*for(PostModel postModel : pagePostModel.getContent())
        {
            createPostResponses.add(new CreatePostResponse(postModel.getText(),postModel.getUsermodel().getUserName(),postModel.getId()));

        }*/
        return cpr;
    }

    public CreatePostResponse getOnePost(String postid)
    {

        PostModel postModel = postRepo.getById(postid);
        CreatePostResponse cpr = new CreatePostResponse(postModel.getText(),postModel.getUsermodel().getUserName(),postModel.getId());
        return cpr;
    }

    public List<CreatePostResponse> getUserPosts(String username,int pageNumber)
    {

        UserModel usermodel = userRepo.findByUsername(username);
        if(usermodel == null)
        {
            throw new UserNotFound("user does not exsist");
        }

        Pageable pageable = PageRequest.of(pageNumber,10);
        Page<PostModel> userModelPage = postRepo.getUserPosts(usermodel.getId(),pageable);
        List<CreatePostResponse> cpr = userModelPage.toList().stream().map(c ->
                new CreatePostResponse(c.getText(),c.getUsermodel().getUserName(),c.getId())).toList();
        /*List<CreatePostResponse> createPostResponses = new ArrayList<>();
        for(PostModel pm : userModelPage.getContent())
        {
            createPostResponses.add(new CreatePostResponse(pm.getText(),pm.getUsermodel().getUserName(),pm.getId()));
        }*/
        return cpr;

    }
}
