package com.example.GroupProject_4.Controllers;

import com.example.GroupProject_4.Models.UserModel;
import com.example.GroupProject_4.Request.UserRequest.UserRegisterRequest;
import com.example.GroupProject_4.Services.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/Register")
    public UserModel register(@RequestBody @Valid UserRegisterRequest registerRequest)
    {
        return userService.registerUser(registerRequest);

    }
}
