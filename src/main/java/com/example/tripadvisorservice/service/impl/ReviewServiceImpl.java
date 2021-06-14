package com.example.tripadvisorservice.service.impl;

import com.example.tripadvisorservice.controller.dto.ReviewDto;
import com.example.tripadvisorservice.entity.Review;
import com.example.tripadvisorservice.repo.ReviewRepository;
import com.example.tripadvisorservice.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public List<ReviewDto> getLastReviews() {
        return reviewRepository.getLastReviews();
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }
}
