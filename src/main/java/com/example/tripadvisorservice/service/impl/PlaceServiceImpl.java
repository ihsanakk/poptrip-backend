package com.example.tripadvisorservice.service.impl;

import com.example.tripadvisorservice.controller.dto.PlaceDto;
import com.example.tripadvisorservice.entity.Place;
import com.example.tripadvisorservice.entity.Review;
import com.example.tripadvisorservice.entity.enums.PlaceType;
import com.example.tripadvisorservice.repo.PlaceRepository;
import com.example.tripadvisorservice.service.PlaceService;
import com.example.tripadvisorservice.service.utils.ServiceDateUtils;
import lombok.RequiredArgsConstructor;
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
    public List<Review> getPlaceReviews(int placeId) {
        Place place = placeRepository.findById(placeId).orElseThrow(()-> new RuntimeException("Place Not Found -> PlaceId: " + placeId));
        return place.getReviews();
    }

    @Override
    public Place getPlaceById(int placeId) {
        Place place = placeRepository.findById(placeId).orElseThrow(()-> new RuntimeException("Place Not Found -> PlaceId: " + placeId));
        return place;
    }

    @Override
    public List<Place> getAllHotels() {
        return placeRepository.findAllByPlaceType(PlaceType.HOTEL);
    }

    @Override
    public List<Place> getAllRestaurants() {
        return placeRepository.findAllByPlaceType(PlaceType.RESTAURANT);
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
