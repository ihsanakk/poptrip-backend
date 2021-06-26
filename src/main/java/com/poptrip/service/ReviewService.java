package com.poptrip.service;

import com.poptrip.controller.dto.ReviewDto;
import com.poptrip.entity.Review;

import java.util.List;

public interface ReviewService {

    List<ReviewDto> getLastReviews();

    List<Review> getAllReviews();



}
