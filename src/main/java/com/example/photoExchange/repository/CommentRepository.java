package com.example.photoExchange.repository;

import com.example.photoExchange.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query("SELECT c FROM Comment c WHERE c.image.id = ?1")
    List<Comment> findAllByImageId(int imageId);
}
