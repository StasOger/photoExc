package com.example.photoExchange.controllers;

import com.example.photoExchange.dto.CommentDtoIn;
import com.example.photoExchange.dto.CommentDtoOut;
import com.example.photoExchange.models.Comment;
import com.example.photoExchange.models.Image;
import com.example.photoExchange.repository.CommentRepository;
import com.example.photoExchange.repository.ImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.Optional;

@RestController
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ImagesRepository imagesRepository;

//    @GetMapping({"/api/image/{imageId}/comment"})
//    public ResponseEntity<List<CommentDtoOut>> getComments(@PathVariable int imageId){
//        List<Comment> comments = commentRepository.findAllByImageId(imageId);
//        return new ResponseEntity<>(comments.stream().map(c -> new CommentDtoOut(c.getId(), c.getDate(), c.getText())).collect(Collectors.toList()), HttpStatus.OK);
//    }

    @PostMapping({"/api/image/{imageId}/comment"})
    public ResponseEntity<CommentDtoOut> addComment(@PathVariable int imageId, @RequestBody CommentDtoIn commentDtoIn){
        Optional<Image> imageOptional = imagesRepository.findById(imageId);
        if (imageOptional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Image image = imageOptional.get();

        Comment comment = new Comment(0, LocalDateTime.now().getLong(ChronoField.CLOCK_HOUR_OF_DAY), commentDtoIn.getText());
        comment.setImage(image);
        commentRepository.save(comment);

        image.getComments().add(comment);
        return new ResponseEntity<>(new CommentDtoOut(comment.getId(), comment.getDate(), comment.getText()), HttpStatus.OK);
    }

    @PostMapping({"/api/image/{imageId}/comment/{commentId}"})
    public ResponseEntity<CommentDtoOut> deleteComment(@PathVariable int imageId, @PathVariable int commentId){
        Optional<Image> imageOptional = imagesRepository.findById(imageId);
        if (imageOptional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        if (commentOptional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Comment comment = commentOptional.get();
        commentRepository.delete(comment);

        return new ResponseEntity<>(new CommentDtoOut(comment.getId(), comment.getDate(), comment.getText()), HttpStatus.OK);
    }
}
