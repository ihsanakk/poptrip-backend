package com.example.tripadvisorservice.repo;

import com.example.tripadvisorservice.entity.Place;
import com.example.tripadvisorservice.entity.enums.PlaceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place,Integer> {


    List<Place> findAllByPlaceType(PlaceType placeType);

    Page<Place> findAllByPlaceType(PlaceType placeType, Pageable pageable);

    List<Place> findAllByPlaceTitleContainsOrPlaceDescriptionContains(String placeType,String description);
}
