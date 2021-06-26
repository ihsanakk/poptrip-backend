package com.poptrip.service.impl;

import com.poptrip.controller.dto.ReviewDto;
import com.poptrip.entity.Review;
import com.poptrip.repo.ReviewRepository;
import com.poptrip.service.ReviewService;
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
