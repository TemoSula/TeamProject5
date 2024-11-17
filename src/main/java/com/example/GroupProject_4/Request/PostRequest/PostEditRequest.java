package com.example.GroupProject_4.Request.PostRequest;

import lombok.Getter;
import lombok.Setter;



public record PostEditRequest (String username, String postId, String text){
}
