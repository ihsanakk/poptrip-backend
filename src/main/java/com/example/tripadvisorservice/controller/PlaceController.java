package com.example.tripadvisorservice.controller;


import com.example.tripadvisorservice.controller.dto.PlaceDto;
import com.example.tripadvisorservice.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*",maxAge = 3600)
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/place")
public class PlaceController {

    private final PlaceService placeService;


    @GetMapping("/getAllPlaces")
    private ResponseEntity<?>  getAllPlaces(){
        return ResponseEntity.ok().body(placeService.getAllPlaces());
    }

    @GetMapping("/getAllPlacesPageable")
    private ResponseEntity<?>  getAllPlacesPageable(int pageNo, int pageSize){
        return ResponseEntity.ok().body(placeService.getAllPlacesPageable(pageNo,pageSize));
    }

    @GetMapping("/getPlaceReviews/{placeId}")
    private ResponseEntity<?>  getPlaceReviews(@PathVariable int placeId){

        return ResponseEntity.ok().body(placeService.getPlaceReviews(placeId));
    }

    @GetMapping("/getPlaceReviewsPageable/{placeId}")
    private ResponseEntity<?> getPlaceReviewsPageable(@PathVariable int placeId, int pageNo, int pageSize){
        return ResponseEntity.ok().body(placeService.getPlaceReviewsPageable(placeId,pageNo,pageSize));
    }

    @GetMapping("/getPlaceById/{placeId}")
    private ResponseEntity<?>  getPlaceById(@PathVariable int placeId){
        return ResponseEntity.ok().body(placeService.getPlaceById(placeId));
    }

    @GetMapping("/getAllHotels")
    private ResponseEntity<?>  getAllHotels(){
        return ResponseEntity.ok().body(placeService.getAllHotels());
    }

    @GetMapping("/getAllHotelsPageable")
    private ResponseEntity<?>  getAllHotelsPageable(int pageNo, int pageSize){
        return ResponseEntity.ok().body(placeService.getAllHotelsPageable(pageNo,pageSize));
    }

    @GetMapping("/getAllRestaurants")
    private ResponseEntity<?>  getAllRestaurant(){
        return ResponseEntity.ok().body(placeService.getAllRestaurants());
    }

    @GetMapping("/getAllRestaurantsPageable")
    private ResponseEntity<?>  getAllRestaurantsPageable(int pageNo, int pageSize){
        return ResponseEntity.ok().body(placeService.getAllRestaurantPageable(pageNo,pageSize));
    }

    @PostMapping("savePlace")
    private ResponseEntity<?>  savePlace(@RequestBody PlaceDto placeDto){
        return ResponseEntity.ok().body(placeService.savePlace(placeDto));
    }

    @PutMapping("/updatePlaceTitle")
    private ResponseEntity<?>  updatePlaceTitle(int placeId, @RequestBody PlaceDto placeDto){
        return ResponseEntity.ok().body(placeService.updatePlaceTitle(placeId,placeDto));
    }

    @PutMapping("/updatePlaceImgUrl")
    private ResponseEntity<?>  updatePlaceImgUrl(int placeId,@RequestBody PlaceDto placeDto){
        return ResponseEntity.ok().body(placeService.updatePlaceImgUrl(placeId,placeDto));
    }

    @PutMapping("/updatePlaceDescription")
    private ResponseEntity<?> updatePlaceDescription(int placeId,@RequestBody PlaceDto placeDto){
        return ResponseEntity.ok().body(placeService.updatePlaceDescription(placeId,placeDto));
    }

    @GetMapping("/searchPlace")
    private ResponseEntity<?> searchPlaces(String keyword){
        return ResponseEntity.ok().body(placeService.searchPlaceByTitleAndDescription(keyword));
    }

    @GetMapping("/getMostLikedHotels")
    private ResponseEntity<?> getMostLikedHotels(){
        return ResponseEntity.ok().body(placeService.getMostLikedHotels());
    }

    @GetMapping("/getMostLikedRestaurants")
    private ResponseEntity<?> getMostLikedRestaurants(){
        return ResponseEntity.ok().body(placeService.getMostLikedRestaurants());
    }

    @GetMapping("/discoverPlaces")
    private ResponseEntity<?> discoverPlaces(){
        return ResponseEntity.ok().body(placeService.discoverPlaces());
    }

}
