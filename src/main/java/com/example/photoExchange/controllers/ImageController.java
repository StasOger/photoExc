package com.example.photoExchange.controllers;

import com.example.photoExchange.dto.ImageDtoIn;
import com.example.photoExchange.dto.ImageDtoOut;
import com.example.photoExchange.models.Image;
import com.example.photoExchange.repository.ImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ImageController {
    @Autowired
    private ImagesRepository imagesRepository;

    @GetMapping({"/api/image"})
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<ImageDtoOut>> getImages() {
        List<Image> images = imagesRepository.findAll();
        return new ResponseEntity<>(images.stream()
                .map(i -> new ImageDtoOut(i.getId(), i.getUrl(), i.getDate(), i.getLat(), i.getLng()))
                .collect(Collectors.toList()), HttpStatus.OK);
    }
    @PostMapping({"/api/image"})
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<ImageDtoOut> addImage(@RequestBody ImageDtoIn imageDtoIn){

        Image image = new Image(0, imageDtoIn.getBase64Image(), imageDtoIn.getDate(), imageDtoIn.getLat(), imageDtoIn.getLng(), "string");
        imagesRepository.save(image);

        return new ResponseEntity<>(new ImageDtoOut(image.getId(), image.getUrl(), image.getDate(), image.getLat(), image.getLng()), HttpStatus.OK);
    }

    @PostMapping({"/api/image/{id}"})
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ImageDtoOut> deleteImage(@PathVariable int id){
        Optional<Image> imageOptional = imagesRepository.findById(id);
        if (imageOptional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Image image = imageOptional.get();
        imagesRepository.delete(image);

        return new ResponseEntity<>(new ImageDtoOut(image.getId(), image.getUrl(), image.getDate(), image.getLat(), image.getLng()), HttpStatus.OK);
    }
}