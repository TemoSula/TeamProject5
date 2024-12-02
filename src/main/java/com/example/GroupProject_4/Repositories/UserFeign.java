package com.example.GroupProject_4.Repositories;

import com.example.GroupProject_4.Request.ReqresUserRequest.ReqresUserModelRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "UserFeign", url = "https://reqres.in/api")
public interface UserFeign {

    @GetMapping("/users")
    ReqresUserModelRequest GetAllUsers(@RequestParam("page") int page, @RequestParam("per_page") int perPage);
    
}
