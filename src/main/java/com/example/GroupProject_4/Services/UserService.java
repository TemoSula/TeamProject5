package com.example.GroupProject_4.Services;

import com.example.GroupProject_4.Models.UserModel;
import com.example.GroupProject_4.Repositories.UserRepostory;
import com.example.GroupProject_4.Request.UserRequest.UserRegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepostory userrepo;


    public UserModel registerUser(UserRegisterRequest registerRequest)
    {
        String generateUserId = UUID.randomUUID().toString();
     UserModel um = userrepo.findByUsername(registerRequest.username());
     if(um != null)
     {
         throw new RuntimeException("User Already exsist");
     }
     UserModel userModel = new UserModel();
     userModel.setId(generateUserId);
     userModel.setUserName(registerRequest.username());
     userModel.setFirstName(registerRequest.firstname());
     userModel.setLastName(registerRequest.lastname());
     userModel.setDateOfBirthday(registerRequest.dateofbirthday());
     return userrepo.save(userModel);
    }
}
