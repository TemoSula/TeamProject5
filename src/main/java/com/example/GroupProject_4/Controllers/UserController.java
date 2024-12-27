package com.example.GroupProject_4.Controllers;

import com.example.GroupProject_4.Exceptions.UserNotFound;
import com.example.GroupProject_4.Models.UserModel;
//import com.example.GroupProject_4.Repositories.UserFeign;
import com.example.GroupProject_4.Repositories.CommentRepository;
import com.example.GroupProject_4.Repositories.PostRepository;
import com.example.GroupProject_4.Repositories.UserFeign;
import com.example.GroupProject_4.Repositories.UserRepostory;
import com.example.GroupProject_4.Request.ReqresUserRequest.ReqresUserModelRequest;
import com.example.GroupProject_4.Request.UserRequest.UserRegisterRequest;
import com.example.GroupProject_4.Services.JwtService;
import com.example.GroupProject_4.Services.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@EnableMethodSecurity
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserFeign userFeign;


    @PostMapping("/register")
    public UserModel register(@RequestBody @Valid UserRegisterRequest registerRequest)
    {
        return userService.registerUser(registerRequest);

    }
    @GetMapping("/getAllUsers{page}{per_page}")
    public ReqresUserModelRequest getAllUserFromReqres(@RequestParam("page") int page, @RequestParam("per_page") int perPage)
    {
        return userFeign.GetAllUsers(page, perPage);
    }

    @DeleteMapping("/remove")
    public void removeUserById()
    {
        userService.removeUserById();
    }

    @GetMapping("/login")
    public String loginUser(@RequestParam("username") String username, @RequestParam("password") String password)
    {
        return userService.login(username,password);
    }


}
