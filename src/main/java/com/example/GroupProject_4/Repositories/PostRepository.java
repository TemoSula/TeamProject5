package com.example.GroupProject_4.Repositories;

import com.example.GroupProject_4.Models.PostModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<PostModel, String> {

    @Query("select pm from PostModel pm where pm.id = :postid")
    PostModel getById(@Param("postid") String postid);

    @Query("SELECT pm FROM PostModel pm WHERE pm.id = :postid AND pm.usermodel.id = :userid")
    PostModel getUsernameAndPostId(@Param("postid") String postid, @Param("userid") String userid);
    @Query("select pm from PostModel pm")
    Page<PostModel> getAllPost(Pageable pageable);

    @Query("select pm from PostModel pm where pm.usermodel.id = :userId")
    Page<PostModel> getUserPosts(@Param("userId") String userId, Pageable pageable);


}
