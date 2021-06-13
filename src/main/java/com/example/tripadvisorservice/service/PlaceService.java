package com.example.tripadvisorservice.service;

import com.example.tripadvisorservice.controller.dto.PlaceDto;
import com.example.tripadvisorservice.entity.Place;
import com.example.tripadvisorservice.entity.Review;
import com.example.tripadvisorservice.entity.enums.PlaceType;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PlaceService {

    List<Place> getAllPlaces();

    Page<Place> getAllPlacesPageable(int pageNo, int pageSize);

    List<Review> getPlaceReviews(int placeId);

    Page<Review> getPlaceReviewsPageable(int placeId, int pageNo, int pageSize);

    Place getPlaceById(int placeId);

    List<Place> getAllHotels();

    Page<Place> getAllHotelsPageable(int pageNo, int pageSize);

    List<Place> getAllRestaurants();

    Page<Place> getAllRestaurantPageable(int pageNo, int pageSize);

    Place savePlace(PlaceDto placeDto);

    Place updatePlaceTitle(int placeId,PlaceDto placeDto);

    Place updatePlaceDescription(int placeId, PlaceDto placeDto);

    Place updatePlaceImgUrl(int placeId, PlaceDto placeDto);

    List<Place> searchPlaceByTitleAndDescription(String searchedValue);


}
