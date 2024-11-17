package com.example.GroupProject_4.Services;

import com.example.GroupProject_4.Models.CommentModel;
import com.example.GroupProject_4.Models.PostModel;
import com.example.GroupProject_4.Models.UserModel;
import com.example.GroupProject_4.Repositories.CommentRepository;
import com.example.GroupProject_4.Repositories.PostRepository;
import com.example.GroupProject_4.Repositories.UserRepostory;
import com.example.GroupProject_4.Request.CommentRequest.DeleteCommentRequest;
import com.example.GroupProject_4.Request.CommentRequest.EditCommentRequest;
import com.example.GroupProject_4.Request.CommentRequest.WriteCommentRequest;
import com.example.GroupProject_4.Response.CommentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CommentService{

    @Autowired
    UserRepostory userRepo;

    @Autowired
    PostRepository postRepo;

    @Autowired
    CommentRepository commentRepo;



    public CommentResponse writeComment(WriteCommentRequest commentRequest/*String username, UUID postid, CommentModel comment*/)
    {
        String generateId = UUID.randomUUID().toString();
        UserModel userModel = userRepo.findByUsername(commentRequest.username());
        if(userModel == null)
        {
            throw new RuntimeException("user is not exsist");
        }
        PostModel postModel = postRepo.getById(commentRequest.postid());
        if(postModel == null)
        {
            throw new RuntimeException("This post does not exsist");
        }

        CommentModel commentModel = new CommentModel();
        commentModel.setId(generateId);
        commentModel.setUserModel(userModel);
        commentModel.setPostModel(postModel);
        commentModel.setText(commentRequest.text());
        commentRepo.save(commentModel);
        return new CommentResponse(commentModel.getText(),commentModel.getPostModel().getText(),
                commentModel.getPostModel().getUsermodel().getUserName(),commentModel.getUserModel().getUserName());
    }

    public void deleteComment(DeleteCommentRequest deleteCommentRequest)
    {


        UserModel userModel = userRepo.findByUsername(deleteCommentRequest.username());
        if(userModel == null)
        {
            throw new RuntimeException("user is not exsist");
        }
        PostModel postModel = postRepo.getById(deleteCommentRequest.postid());
        if(postModel == null)
        {
            throw new RuntimeException("This post does not exsist");
        }
        CommentModel comment = commentRepo.findByUserIdAndPostId(userModel.getId(),postModel.getId());
        if(comment == null)
        {
            throw new RuntimeException("this comment does not exsist");
        }
        commentRepo.delete(comment);

    }

    public CommentResponse editComment(EditCommentRequest editCommentRequest)
    {
        UserModel userModel = userRepo.findByUsername(editCommentRequest.username());
        if(userModel == null)
        {
            throw new RuntimeException("user is not exsist");
        }
        PostModel postModel = postRepo.getById(editCommentRequest.postid());
        if(postModel == null)
        {
            throw new RuntimeException("This post does not exsist");
        }
        CommentModel comment = commentRepo.findByUserIdAndPostId(userModel.getId(),postModel.getId());
        if(comment == null)
        {
            throw new RuntimeException("this comment does not exsist");
        }

        comment.setText(editCommentRequest.text());
        commentRepo.save(comment);
        return new CommentResponse(comment.getText(),comment.getPostModel().getText(),comment.getPostModel().getUsermodel().getUserName()
                ,comment.getUserModel().getUserName());
    }

}
