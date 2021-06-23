package com.example.tripadvisorservice.controller;


import com.example.tripadvisorservice.controller.dto.PlaceDto;
import com.example.tripadvisorservice.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*",maxAge = 3600)
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/place")
public class PlaceController {

    private final PlaceService placeService;


    @GetMapping("/getAllPlaces")
    public ResponseEntity<?>  getAllPlaces(){
        return ResponseEntity.ok().body(placeService.getAllPlaces());
    }

    @GetMapping("/getAllPlacesPageable")
    public ResponseEntity<?>  getAllPlacesPageable(int pageNo, int pageSize){
        return ResponseEntity.ok().body(placeService.getAllPlacesPageable(pageNo,pageSize));
    }

    @GetMapping("/getPlaceReviews/{placeId}")
    public ResponseEntity<?>  getPlaceReviews(@PathVariable int placeId){

        return ResponseEntity.ok().body(placeService.getPlaceReviews(placeId));
    }

    @GetMapping("/getPlaceReviewsPageable/{placeId}")
    public ResponseEntity<?> getPlaceReviewsPageable(@PathVariable int placeId, int pageNo, int pageSize){
        return ResponseEntity.ok().body(placeService.getPlaceReviewsPageable(placeId,pageNo,pageSize));
    }

    @GetMapping("/getPlaceById/{placeId}")
    public ResponseEntity<?>  getPlaceById(@PathVariable int placeId){
        return ResponseEntity.ok().body(placeService.getPlaceById(placeId));
    }

    @GetMapping("/getAllHotels")
    public ResponseEntity<?>  getAllHotels(){
        return ResponseEntity.ok().body(placeService.getAllHotels());
    }

    @GetMapping("/getAllHotelsPageable")
    public ResponseEntity<?>  getAllHotelsPageable(int pageNo, int pageSize){
        return ResponseEntity.ok().body(placeService.getAllHotelsPageable(pageNo,pageSize));
    }

    @GetMapping("/getAllRestaurants")
    public ResponseEntity<?>  getAllRestaurant(){
        return ResponseEntity.ok().body(placeService.getAllRestaurants());
    }

    @GetMapping("/getAllRestaurantsPageable")
    public ResponseEntity<?>  getAllRestaurantsPageable(int pageNo, int pageSize){
        return ResponseEntity.ok().body(placeService.getAllRestaurantPageable(pageNo,pageSize));
    }

    @PreAuthorize("hasAnyRole('ROLE_MODERATOR','ROLE_ADMIN')")
    @PostMapping("/savePlace")
    public ResponseEntity<?>  savePlace(@RequestBody PlaceDto placeDto){
        return ResponseEntity.ok().body(placeService.savePlace(placeDto));
    }

    @PutMapping("/updatePlaceTitle")
    public ResponseEntity<?>  updatePlaceTitle(int placeId, @RequestBody PlaceDto placeDto){
        return ResponseEntity.ok().body(placeService.updatePlaceTitle(placeId,placeDto));
    }

    @PutMapping("/updatePlaceImgUrl")
    public ResponseEntity<?>  updatePlaceImgUrl(int placeId,@RequestBody PlaceDto placeDto){
        return ResponseEntity.ok().body(placeService.updatePlaceImgUrl(placeId,placeDto));
    }

    @PutMapping("/updatePlaceDescription")
    public ResponseEntity<?> updatePlaceDescription(int placeId,@RequestBody PlaceDto placeDto){
        return ResponseEntity.ok().body(placeService.updatePlaceDescription(placeId,placeDto));
    }

    @GetMapping("/searchPlace")
    public ResponseEntity<?> searchPlaces(String keyword){
        return ResponseEntity.ok().body(placeService.searchPlaceByTitleAndDescription(keyword));
    }

    @GetMapping("/getMostLikedHotels")
    public ResponseEntity<?> getMostLikedHotels(){
        return ResponseEntity.ok().body(placeService.getMostLikedHotels());
    }

    @GetMapping("/getMostLikedRestaurants")
    public ResponseEntity<?> getMostLikedRestaurants(){
        return ResponseEntity.ok().body(placeService.getMostLikedRestaurants());
    }

    @GetMapping("/discoverPlaces")
    public ResponseEntity<?> discoverPlaces(){
        return ResponseEntity.ok().body(placeService.discoverPlaces());
    }

}
