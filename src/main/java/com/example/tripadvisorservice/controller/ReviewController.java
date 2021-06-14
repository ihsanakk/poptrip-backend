package com.example.tripadvisorservice.controller;

import com.example.tripadvisorservice.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*",maxAge = 3600)
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/getLastReviews")
    public ResponseEntity<?> getLastReviews(){

        return ResponseEntity.ok().body(reviewService.getLastReviews());

    }

    @GetMapping("/getAllReviews")
    public ResponseEntity<?> getAllReviews(){
        return ResponseEntity.ok().body(reviewService.getAllReviews());
    }
}
