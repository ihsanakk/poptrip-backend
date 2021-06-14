package com.example.tripadvisorservice.service;

import com.example.tripadvisorservice.controller.dto.ReviewDto;
import com.example.tripadvisorservice.entity.Review;

import java.util.List;

public interface ReviewService {

    List<ReviewDto> getLastReviews();

    List<Review> getAllReviews();



}
