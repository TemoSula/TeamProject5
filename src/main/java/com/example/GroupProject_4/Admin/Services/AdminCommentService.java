package com.example.GroupProject_4.Admin.Services;

import com.example.GroupProject_4.Admin.Request.AdminDeleteCommentRequest;
import com.example.GroupProject_4.Admin.Request.AdminEditCommentRequest;
import com.example.GroupProject_4.Exceptions.CommentNotFound;
import com.example.GroupProject_4.Exceptions.PostNotFound;
import com.example.GroupProject_4.Exceptions.UserNotFound;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AdminCommentService {

        @Autowired
        UserRepostory userRepo;

        @Autowired
        PostRepository postRepo;

        @Autowired
        CommentRepository commentRepo;


        public void deleteComment(AdminDeleteCommentRequest deleteCommentRequest)
        {


            UserModel userModel = userRepo.findByUsername(deleteCommentRequest.username());
            if(userModel == null)
            {
                throw new UserNotFound("User not found");
            }
            PostModel postModel = postRepo.getById(deleteCommentRequest.postId());
            if(postModel == null)
            {
                throw new PostNotFound("Post not found");
            }
            CommentModel comment = commentRepo.findByUserIdAndPostId(userModel.getId(),postModel.getId(),deleteCommentRequest.commentId());
            if(comment == null)
            {
                throw new CommentNotFound("Comment not found");
            }

            commentRepo.delete(comment);

        }

        public CommentResponse editComment(AdminEditCommentRequest editCommentRequest)
        {

            UserModel userModel = userRepo.findByUsername(editCommentRequest.username());
            if(userModel == null)
            {
                throw new UserNotFound("user is not exsist");
            }
            PostModel postModel = postRepo.getById(editCommentRequest.postId());
            if(postModel == null)
            {
                throw new PostNotFound("This post does not exsist");
            }
            CommentModel comment = commentRepo.findByUserIdAndPostId(userModel.getId(),postModel.getId(),editCommentRequest.commentId());
            if(comment == null)
            {
                throw new CommentNotFound("this comment does not exsist");
            }

            comment.setText(editCommentRequest.text());
            commentRepo.save(comment);
            return new CommentResponse(comment.getText(),comment.getPostModel().getText(),comment.getPostModel().getUsermodel().getUserName()
                    ,comment.getUserModel().getUserName());

        }

    }

