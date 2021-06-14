package com.example.tripadvisorservice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReviewDto {

    private Integer reviewId;
    private String reviewText;
    private Date createdAt;
    private Date updateAt;

    private Integer userId;
    private String username;

    private Integer placeId;
    private String placeName;
    private String placeImageUrl;


}
