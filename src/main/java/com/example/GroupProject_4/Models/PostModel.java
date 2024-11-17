package com.example.GroupProject_4.Models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "posts")
public class PostModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "text")
    private String text;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel usermodel;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UserModel getUsermodel() {
        return usermodel;
    }

    public void setUsermodel(UserModel usermodel) {
        this.usermodel = usermodel;
    }
}
