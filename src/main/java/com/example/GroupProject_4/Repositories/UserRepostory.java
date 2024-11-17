package com.example.GroupProject_4.Repositories;

import com.example.GroupProject_4.Models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepostory extends JpaRepository<UserModel, UUID> {
    @Query("select um from UserModel um where um.userName = :username")
    UserModel findByUsername(@Param("username") String username); //boolean

}
