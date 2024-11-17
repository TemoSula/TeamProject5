package com.example.GroupProject_4.Request.UserRequest;

import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

public record UserRegisterRequest(String firstname, String lastname, String username, LocalDate dateofbirthday) {
}
