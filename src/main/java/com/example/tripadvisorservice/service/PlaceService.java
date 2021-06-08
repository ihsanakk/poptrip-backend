package com.example.tripadvisorservice.service;

import com.example.tripadvisorservice.controller.dto.PlaceDto;
import com.example.tripadvisorservice.entity.Place;
import com.example.tripadvisorservice.entity.Review;
import com.example.tripadvisorservice.entity.enums.PlaceType;

import java.util.List;

public interface PlaceService {

    List<Place> getAllPlaces();

    List<Review> getPlaceReviews(int placeId);

    Place getPlaceById(int placeId);

    List<Place> getAllHotels();

    List<Place> getAllRestaurants();

    Place savePlace(PlaceDto placeDto);

    Place updatePlaceTitle(int placeId,PlaceDto placeDto);

    Place updatePlaceDescription(int placeId, PlaceDto placeDto);

    Place updatePlaceImgUrl(int placeId, PlaceDto placeDto);

    List<Place> searchPlaceByTitleAndDescription(String searchedValue);


}
