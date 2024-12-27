package com.example.GroupProject_4.Admin.Controllers;

import com.example.GroupProject_4.Admin.Request.UserStatusResponse;
import com.example.GroupProject_4.Admin.Services.AdminUserService;
import com.example.GroupProject_4.Models.UserModel;
import com.example.GroupProject_4.enums.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Admin")
@EnableMethodSecurity
public class AdminUserController {

    @Autowired
    AdminUserService adminService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/changeStatus")
    public UserStatusResponse changeStatus(@RequestParam("username") String username, @RequestParam("status")UserStatus userStatus){
        return adminService.changeUserStatus(username,userStatus);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/RemoveUserByUsername")
    public void removeUserByusername(String username){
        adminService.removeUserByUsername(username);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/removeAllUser")
    public void removeAlllUser()
    {
        adminService.removeUsers();
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getWithId/{id}")
    public UserModel getById(@RequestParam("username")String username)
    {
        return adminService.getUserById(username);
    }


}
