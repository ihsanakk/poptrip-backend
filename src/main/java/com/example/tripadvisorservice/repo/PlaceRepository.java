package com.example.tripadvisorservice.repo;

import com.example.tripadvisorservice.controller.dto.ReviewDto;
import com.example.tripadvisorservice.entity.Place;
import com.example.tripadvisorservice.entity.enums.PlaceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;


public interface PlaceRepository extends JpaRepository<Place,Integer> {


    List<Place> findAllByPlaceType(PlaceType placeType);

    Page<Place> findAllByPlaceType(PlaceType placeType, Pageable pageable);

    List<Place> findAllByPlaceTitleContainsOrPlaceDescriptionContains(String placeType,String description);

    @Query(value = "select new com.example.tripadvisorservice.controller.dto.ReviewDto(r.id,r.reviewText,r.createdAt,r.updatedAt, u.id,u.username, p.id, p.placeTitle,p.imageUrl) from Place p join p.reviews r join r.user u where p.id=:place_id")
    List<ReviewDto> getPlaceReviews(int place_id);

//    @Transactional
//    @Modifying
//    @Query(value = "update places set num_of_reviews = num_of_reviews + 1 WHERE place_id=:place_id", nativeQuery = true)
//    Integer increaseNumberOfReviews(@Param("place_id") int place_id);

    @Query(value="select * from places where place_type='HOTEL' order by likes asc limit 3;",nativeQuery = true)
    List<Place> getMostLikedHotels();


    @Query(value="select * from places where place_type='RESTAURANT' order by likes asc limit 3;",nativeQuery = true)
    List<Place> getMostLikedRestaurants();

    @Query(value = "select * from places order by RANDOM() LIMIT 3", nativeQuery = true)
    List<Place> discoverPlaces();
}
