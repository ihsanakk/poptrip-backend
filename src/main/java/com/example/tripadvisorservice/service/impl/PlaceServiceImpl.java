package com.example.tripadvisorservice.service.impl;

import com.example.tripadvisorservice.advice.exception.PlaceNotFoundException;
import com.example.tripadvisorservice.controller.dto.PlaceDto;
import com.example.tripadvisorservice.controller.dto.ReviewDto;
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
import org.springframework.data.domain.jaxb.SpringDataJaxb;
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
    public List<ReviewDto> getPlaceReviews(int placeId) {
        placeRepository.findById(placeId).orElseThrow(()->new PlaceNotFoundException("Place Not Found: "+placeId));
        return placeRepository.getPlaceReviews(placeId);
    }

    @Override
    public Page<ReviewDto> getPlaceReviewsPageable(int placeId, int pageNo, int pageSize) {
        Place place = placeRepository.findById(placeId).orElseThrow(()->  new PlaceNotFoundException("Place Not Found: "+ placeId));
        List<ReviewDto> reviews = placeRepository.getPlaceReviews(placeId);
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        return new PageImpl<>(reviews,pageable,reviews.size());
    }

    @Override
    public Place getPlaceById(int placeId) {
        return placeRepository.findById(placeId).orElseThrow(()-> new PlaceNotFoundException("Place Not Found: "+ placeId));
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
        place.setNumberOfReviews(0);
        return placeRepository.save(place);
    }

    @Override
    public Place updatePlaceTitle(int placeId, PlaceDto placeDto) {
        Place place = placeRepository.findById(placeId).orElseThrow(()->  new PlaceNotFoundException("Place Not Found: "+ placeId));
        place.setPlaceTitle(placeDto.getPlaceTitle());
        place.setUpdatedAt(serviceDateUtils.getTimeStamp());
        return placeRepository.save(place);
    }

    @Override
    public Place updatePlaceDescription(int placeId, PlaceDto placeDto) {
        Place place = placeRepository.findById(placeId).orElseThrow(()->  new PlaceNotFoundException("Place Not Found: "+ placeId));
        place.setPlaceDescription(placeDto.getPlaceDescription());
        place.setUpdatedAt(serviceDateUtils.getTimeStamp());
        return placeRepository.save(place);
    }

    @Override
    public Place updatePlaceImgUrl(int placeId, PlaceDto placeDto) {
        Place place = placeRepository.findById(placeId).orElseThrow(()->  new PlaceNotFoundException("Place Not Found: "+ placeId));
        place.setImageUrl(placeDto.getPlaceImgUrl());
        place.setUpdatedAt(serviceDateUtils.getTimeStamp());
        return placeRepository.save(place);
    }

    @Override
    public List<Place> searchPlaceByTitleAndDescription(String searchedValue) {
        return placeRepository.findAllByPlaceTitleContainsOrPlaceDescriptionContains(searchedValue,searchedValue);
    }

    @Override
    public List<Place> getMostLikedHotels() {
        return placeRepository.getMostLikedHotels();
    }

    @Override
    public List<Place> getMostLikedRestaurants() {
        return placeRepository.getMostLikedRestaurants();
    }

    @Override
    public List<Place> discoverPlaces() {
        return placeRepository.discoverPlaces();
    }
}
