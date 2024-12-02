package com.example.GroupProject_4.Repositories;

import com.example.GroupProject_4.Models.UserModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface UserRepostory extends JpaRepository<UserModel, UUID> {
    @Query("select um from UserModel um where um.userName = :username")
    UserModel findByUsername(@Param("username") String username); //boolean

    @Query("select um from UserModel um where um.id = :userid")
    UserModel getUserById(@Param("userid") String userid);

    @Transactional
    @Modifying
    @Query("delete from UserModel um where um.id = :userid")
    void removeUserById(@Param("userid") String userid);

}
