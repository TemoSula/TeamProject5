package com.example.GroupProject_4.Services;

import com.example.GroupProject_4.Exceptions.UserAlreadyExsist;
import com.example.GroupProject_4.Exceptions.UserNotFound;
import com.example.GroupProject_4.Models.CommentModel;
import com.example.GroupProject_4.Models.PostModel;
import com.example.GroupProject_4.Models.UserModel;
import com.example.GroupProject_4.Repositories.CommentRepository;
import com.example.GroupProject_4.Repositories.PostRepository;
import com.example.GroupProject_4.Repositories.UserFeign;
import com.example.GroupProject_4.Repositories.UserRepostory;
import com.example.GroupProject_4.Request.ReqresUserRequest.Data;
import com.example.GroupProject_4.Request.ReqresUserRequest.ReqresUserModelRequest;
import com.example.GroupProject_4.Request.UserRequest.UserRegisterRequest;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class UserService {

    /*@Autowired
    Logger log;*/

    @Autowired
    UserRepostory userrepo;

    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepo;

    @Autowired
    CommentRepository commentRepo;

    @Autowired
    UserFeign userFeign;

    @Transactional
    public void removeUserAndPost(String userid)
    {

        commentRepo.RemoveCommentByUserId(userid);


        PostModel postModel = postRepo.getPostByUserId(userid);

        commentRepo.removeByPostId(postModel.getId());
        postRepo.removePostByUserId(userid);

        /*postService.deletePostAndComments(postModel.getId());*/

        userrepo.removeUserById(userid);

    }


    public UserModel registerUser(UserRegisterRequest registerRequest)
    {

         UserModel um = userrepo.findByUsername(registerRequest.username());
         if(um != null)
        {
         throw new UserAlreadyExsist("User Already exsist");
        }
     UserModel userModel = new UserModel();
     userModel.setUserName(registerRequest.username());
     userModel.setFirstName(registerRequest.firstname());
     userModel.setLastName(registerRequest.lastname());
     userModel.setDateOfBirthday(registerRequest.dateofbirthday());
     return userrepo.save(userModel);
    }



    @PostConstruct
    public void getUsersFromReqres()
    {
        ReqresUserModelRequest request1 = userFeign.GetAllUsers(0,5);
        ReqresUserModelRequest request2 = userFeign.GetAllUsers(0,request1.getTotal());
        for(int i = 0; i < request2.getTotal();i++)
        {
            Data data = request2.getData().get(i);
            String userName = data.getFirst_name() + " " + data.getLast_name();//Change with email
            UserModel userModel = userrepo.findByUsername(userName);
            if (userModel == null) {
            UserModel userModel1 = new UserModel();
            userModel1.setUserName(userName);
            userModel1.setFirstName(data.getFirst_name());
            userModel1.setLastName(data.getLast_name());
            userrepo.save(userModel1);
            }
            }
        System.out.println("everything will be done");
    }

    /*request2.getData().stream()
        .map(data -> {
            String userName = data.getFirst_name() + " " + data.getLast_name();
            UserModel existingUser = userrepo.findByUsername(userName);
            if (existingUser == null) {
                UserModel newUser = new UserModel();
                newUser.setUserName(userName);
                newUser.setFirstName(data.getFirst_name());
                newUser.setLastName(data.getLast_name());
                return newUser; // Mark for saving
            }
            return null;//იუზერი არსებობს
        })
        .filter(userModel -> userModel != null) // Only process new users
        .forEach(userrepo::save); // Save each new user to the database

System.out.println("everything will be done");*/



        public void removeUsers()
        {            userrepo.deleteAll();
        }
    public void removeUserById(String id){

        UserModel userModel = userrepo.getUserById(id);
        if(userModel == null)
        {
            throw new RuntimeException("user not found");
        }
        removeUserAndPost(id);
    }

}
