package com.example.photoExchange.repository;

import com.example.photoExchange.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ImagesRepository extends JpaRepository<Image, Integer> {

}
