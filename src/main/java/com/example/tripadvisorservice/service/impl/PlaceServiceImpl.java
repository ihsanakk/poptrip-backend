package com.example.tripadvisorservice.service.impl;

import com.example.tripadvisorservice.controller.dto.PlaceDto;
import com.example.tripadvisorservice.entity.Place;
import com.example.tripadvisorservice.entity.Review;
import com.example.tripadvisorservice.entity.enums.PlaceType;
import com.example.tripadvisorservice.repo.PlaceRepository;
import com.example.tripadvisorservice.service.PlaceService;
import com.example.tripadvisorservice.service.utils.ServiceDateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PlaceServiceImpl implements PlaceService {


    private final ServiceDateUtils serviceDateUtils;
    private final PlaceRepository placeRepository;

    @Override
    public List<Place> getAllPlaces() {
        return placeRepository.findAll();
    }

    @Override
    public Page<Place> getAllPlacesPageable(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return placeRepository.findAll(pageable);
    }

    @Override
    public List<Review> getPlaceReviews(int placeId) {
        Place place = placeRepository.findById(placeId).orElseThrow(()-> new RuntimeException("Place Not Found -> PlaceId: " + placeId));
        return place.getReviews();
    }

    @Override
    public Page<Review> getPlaceReviewsPageable(int placeId, int pageNo, int pageSize) {
        Place place = placeRepository.findById(placeId).orElseThrow(()-> new RuntimeException("Place Not Found -> PlaceId: " + placeId));
        List<Review> reviews = place.getReviews();
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        return new PageImpl<>(reviews,pageable,reviews.size());
    }

    @Override
    public Place getPlaceById(int placeId) {
        return placeRepository.findById(placeId).orElseThrow(()-> new RuntimeException("Place Not Found -> PlaceId: " + placeId));
    }

    @Override
    public List<Place> getAllHotels() {
        return placeRepository.findAllByPlaceType(PlaceType.HOTEL);
    }

    @Override
    public Page<Place> getAllHotelsPageable(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return placeRepository.findAllByPlaceType(PlaceType.HOTEL,pageable);
    }

    @Override
    public List<Place> getAllRestaurants() {
        return placeRepository.findAllByPlaceType(PlaceType.RESTAURANT);
    }

    @Override
    public Page<Place> getAllRestaurantPageable(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return placeRepository.findAllByPlaceType(PlaceType.RESTAURANT,pageable);
    }

    @Override
    public Place savePlace(PlaceDto placeDto) {
        Place place = new Place();
        place.setPlaceTitle(placeDto.getPlaceTitle());
        place.setPlaceDescription(placeDto.getPlaceDescription());
        place.setPlaceType(placeDto.getPlaceType());
        place.setImageUrl(placeDto.getPlaceImgUrl());
        place.setCreatedAt(serviceDateUtils.getDate());
        return placeRepository.save(place);
    }

    @Override
    public Place updatePlaceTitle(int placeId, PlaceDto placeDto) {
        Place place = placeRepository.findById(placeId).orElseThrow(()-> new RuntimeException("Place Not Found -> PlaceId: " + placeId));
        place.setPlaceTitle(placeDto.getPlaceTitle());
        place.setUpdatedAt(serviceDateUtils.getTimeStamp());
        return placeRepository.save(place);
    }

    @Override
    public Place updatePlaceDescription(int placeId, PlaceDto placeDto) {
        Place place = placeRepository.findById(placeId).orElseThrow(()-> new RuntimeException("Place Not Found -> PlaceId: " + placeId));
        place.setPlaceDescription(placeDto.getPlaceDescription());
        place.setUpdatedAt(serviceDateUtils.getTimeStamp());
        return placeRepository.save(place);
    }

    @Override
    public Place updatePlaceImgUrl(int placeId, PlaceDto placeDto) {
        Place place = placeRepository.findById(placeId).orElseThrow(()-> new RuntimeException("Place Not Found -> PlaceId: " + placeId));
        place.setImageUrl(placeDto.getPlaceImgUrl());
        place.setUpdatedAt(serviceDateUtils.getTimeStamp());
        return placeRepository.save(place);
    }

    @Override
    public List<Place> searchPlaceByTitleAndDescription(String searchedValue) {
        return placeRepository.findAllByPlaceTitleContainsOrPlaceDescriptionContains(searchedValue,searchedValue);
    }
}
