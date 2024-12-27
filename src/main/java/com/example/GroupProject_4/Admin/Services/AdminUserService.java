package com.example.GroupProject_4.Admin.Services;

import com.example.GroupProject_4.Admin.Request.UserStatusResponse;
import com.example.GroupProject_4.Exceptions.UserNotFound;
import com.example.GroupProject_4.Models.PostModel;
import com.example.GroupProject_4.Models.UserModel;
import com.example.GroupProject_4.Repositories.CommentRepository;
import com.example.GroupProject_4.Repositories.PostRepository;
import com.example.GroupProject_4.Repositories.UserFeign;
import com.example.GroupProject_4.Repositories.UserRepostory;
import com.example.GroupProject_4.Services.PostService;
import com.example.GroupProject_4.enums.UserStatus;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminUserService {


    @Autowired
    UserRepostory userrepo;

    @Autowired
    PostRepository postRepo;

    @Autowired
    CommentRepository commentRepo;


    @Transactional
    public void removeUserAndPost(String userid)
    {

        commentRepo.RemoveCommentByUserId(userid);


        PostModel postModel = postRepo.getPostByUserId(userid);
        if(postModel != null) {
            commentRepo.removeByPostId(postModel.getId());
            postRepo.removePostByUserId(userid);
        }
        /*postService.deletePostAndComments(postModel.getId());*/
        SecurityContextHolder.clearContext();
        userrepo.removeUserById(userid);

    }

    public void removeUsers()
    {            userrepo.deleteAll();
    }

    public void removeUserByUsername(String username){
        UserModel userModel = userrepo.findByUsername(username);
        if(userModel == null)
        {
            throw new RuntimeException("user not found");
        }
        removeUserAndPost(userModel.getId());
    }

    public UserStatusResponse changeUserStatus(String username, UserStatus userStatus)
    {
        UserModel userModel = userrepo.findByUsername(username);
        if(userModel == null)
        {
            throw new UserNotFound("user not found");
        }
        if(userModel.getStatus() == userStatus)
        {
            throw  new UserNotFound("User already have this status");
        }
        userModel.setStatus(userStatus);
        userrepo.save(userModel);
        UserStatusResponse usr = new UserStatusResponse(userModel.getUserName(),userModel.getStatus());
        return usr;
    }

    public UserModel getUserById(String username)
    {
        UserModel userModel = userrepo.findByUsername(username);
        if(userModel == null )
        {
             throw new UserNotFound("user not found");
        }
        return userModel;
    }


}
