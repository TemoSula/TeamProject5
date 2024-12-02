package com.example.GroupProject_4.Request.ReqresUserRequest;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class ReqresUserModelRequest
{

    private int page;
    private int total;
    @JsonProperty("per_page")
    private int perpage;
    @JsonProperty("total_pages")
    private int totalpages;
    @JsonProperty("data")
    private ArrayList<Data> data;
    @JsonProperty("support")
    private Support support;

}
