package com.example.GroupProject_4.Repositories;

import com.example.GroupProject_4.Models.CommentModel;
import com.example.GroupProject_4.Models.PostModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentModel, String> {

    //@Query("SELECT cm FROM CommentModel cm WHERE cm.userModel.id = :userid AND cm.postModel.id = :postid")
    @Query(value = "SELECT * from comments where user_id = :userid and post_id = :postid and id = :commentid",nativeQuery = true)
    CommentModel findByUserIdAndPostId(@Param("userid") String userid, @Param("postid") String postid,@Param("commentid") String commentid);

    @Transactional
    @Modifying
    @Query(value = "delete from comments where post_id = :postId",  nativeQuery = true)
    void removeByPostId(@Param("postId") String postId);
}
