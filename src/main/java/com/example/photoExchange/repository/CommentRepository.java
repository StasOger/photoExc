package com.example.photoExchange.repository;

import com.example.photoExchange.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepository extends JpaRepository<Comment, Integer> {

//    @Query("SELECT comment.text, image.id FROM comment JOIN image ON comment.image = image.id")
//    List<Comment> findAllByImageId(int imageId);

}
