package com.jayplusplus.blackbox.repository;

import com.jayplusplus.blackbox.entity.Post;
import com.jayplusplus.blackbox.model.PostResponseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository  extends JpaRepository<Post,Integer> {

    @Query("SELECT NEW com.jayplusplus.blackbox.model.PostResponseModel(p.postId as postId, p.title as title, p.content as content, p.timestamp as timestamp) " +
            "FROM Post p " +
            "INNER JOIN p.user u " +
            "WHERE u.username = :username")
    List<PostResponseModel> findPostsByUserId(String username);

}
