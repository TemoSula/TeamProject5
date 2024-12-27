package com.example.GroupProject_4.Admin.Request;

import com.example.GroupProject_4.enums.UserStatus;

public record UserStatusResponse(String username, UserStatus userStatus) {
}
