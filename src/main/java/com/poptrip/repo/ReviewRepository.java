package com.poptrip.repo;

import com.poptrip.controller.dto.ReviewDto;
import com.poptrip.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Integer> {


    @Query(value = "select new com.poptrip.controller.dto.ReviewDto(r.id,r.reviewText,r.createdAt,r.updatedAt,u.id,u.username,p.id,p.placeTitle,p.imageUrl) from Review r join r.place p join r.user u order by r.createdAt")
    List<ReviewDto> getLastReviews();


}
