package com.example.GroupProject_4.Request.UserRequest;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

public record UserRegisterRequest
        (@NotNull(message = "firstname can't be null")
         //@Length(min = 2, max = 32,message = "text can't be lower 2 and higher 32")
         //@Min(value = 2, message = "text can't be lower 2")
        //@Max(value = 32,message = "text can't be higher 32")
         @Size(min = 2,max = 32,message = "text can't be lower 2 and higher 32")
         @Pattern(regexp = "^[a-zA-Z0]+$", message = "Firstname must contain only Latin letters")
         @Schema(example = "Enter Firstname")
         String firstname,
         @NotNull(message = "lastname can't be null")
         //@Length(min = 2, max = 32,message = "text can't be lower 2 and higher 32")
         @Size(min = 2,max = 32,message = "text can't be lower 2 and higher 32")
         @Pattern(regexp = "^[a-zA-ZO]+$", message = "Lastname must contain only Latin letters")
         @Schema(example = "Enter Lastname")
         String lastname,
         //@NotNull(message = "username can't be null")
         @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username must contain only Latin letters and numbers.")
         @Schema(example = "Enter Username")
         @Size(min = 4,max = 16,message = "text can't be lower 4 and higher 16")
         String username,


         //@Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Date must follow the format YYYY-MM-DD")
         //@JsonFormat(pattern = "yyyy-MM-DD", shape = JsonFormat.Shape.STRING)
         @PastOrPresent(message = "Birth date must be today or in the past")
         @NotNull(message = "dateofbirthday can't be null")
         LocalDate dateofbirthday)
{}
