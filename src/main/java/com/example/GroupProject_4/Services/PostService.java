package com.example.GroupProject_4.Services;

import com.example.GroupProject_4.Models.PostModel;
import com.example.GroupProject_4.Models.UserModel;
import com.example.GroupProject_4.Repositories.PostRepository;
import com.example.GroupProject_4.Repositories.UserRepostory;
import com.example.GroupProject_4.Request.PostRequest.CreatePostRequest;
import com.example.GroupProject_4.Request.PostRequest.DeletePostRequest;
import com.example.GroupProject_4.Request.PostRequest.PostEditRequest;
import com.example.GroupProject_4.Response.PostResponse.CreatePostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PostService {
    @Autowired
    PostRepository postRepo;

    @Autowired
    UserRepostory userRepo;

    public CreatePostResponse createPost(CreatePostRequest createPostRequest)
    {
        String generatePostId = UUID.randomUUID().toString();
        UserModel userModel = userRepo.findByUsername(createPostRequest.username());
        if(userModel == null)
        {
            throw new RuntimeException("user is not exsist");
        }
        PostModel postModel = new PostModel();
        postModel.setId(generatePostId);
        postModel.setUsermodel(userModel);
        postModel.setText(createPostRequest.text());
        postRepo.save(postModel);
        CreatePostResponse response = new CreatePostResponse(postModel.getText(),userModel.getUserName());
        return response;

    }

    public void deletePost(DeletePostRequest deletePostRequest)
    {
        UserModel userModel = userRepo.findByUsername(deletePostRequest.username());
        if(userModel == null)
        {
            throw new RuntimeException("user is not exsist");
        }
        PostModel postModel = postRepo.getUsernameAndPostId(deletePostRequest.postId(),userModel.getId());

        if(postModel == null)
        {
            throw new RuntimeException("this user doest not have any post");
        }

        postRepo.delete(postModel);


    }


    public CreatePostResponse edit(PostEditRequest postEditRequest)
    {
        UserModel userModel = userRepo.findByUsername(postEditRequest.username());
        if(userModel == null)
        {
            throw new RuntimeException("user is not exsist");
        }
        PostModel postModel = postRepo.getUsernameAndPostId(postEditRequest.postId(),userModel.getId());
        if(postModel == null)
        {
            throw new RuntimeException("this user doest not have any post");
        }

        postModel.setText(postEditRequest.text());
        postRepo.save(postModel);
        return new CreatePostResponse(postModel.getText(),userModel.getUserName());

    }

    public List<CreatePostResponse> getAllPost(int pageNumber)
    {
        Pageable pageable = PageRequest.of(pageNumber,10);
        Page<PostModel> pagePostModel = postRepo.getAllPost(pageable);

        List<CreatePostResponse> createPostResponses = new ArrayList<>();

        for(PostModel postModel : pagePostModel.getContent())
        {
             createPostResponses.add(new CreatePostResponse(postModel.getText(),postModel.getUsermodel().getUserName()));

        }
        return createPostResponses;
    }

    public CreatePostResponse getOnePost(String postid)
    {

        PostModel postModel = postRepo.getById(postid);
        CreatePostResponse cpr = new CreatePostResponse(postModel.getText(),postModel.getUsermodel().getUserName());
        return cpr;
    }

    public List<CreatePostResponse> getUserPosts(String username,int pageNumber)
    {
        UserModel usermodel = userRepo.findByUsername(username);
        if(usermodel == null)
        {
            throw new RuntimeException("user is not exsist");
        }

        Pageable pageable = PageRequest.of(pageNumber,10);
        Page<PostModel> userModelPage = postRepo.getUserPosts(usermodel.getId(),pageable);
        List<CreatePostResponse> createPostResponses = new ArrayList<>();
        for(PostModel pm : userModelPage.getContent())
        {
            createPostResponses.add(new CreatePostResponse(pm.getText(),pm.getUsermodel().getUserName()));
        }
        return createPostResponses;

    }

}
