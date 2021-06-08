package com.example.tripadvisorservice.controller;


import com.example.tripadvisorservice.controller.dto.PlaceDto;
import com.example.tripadvisorservice.entity.Place;
import com.example.tripadvisorservice.entity.Review;
import com.example.tripadvisorservice.repo.PlaceRepository;
import com.example.tripadvisorservice.repo.ReviewRepository;
import com.example.tripadvisorservice.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*",maxAge = 3600)
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/place")
public class PlaceController {

    private final PlaceService placeService;


    @GetMapping("/getALlPlaces")
    public ResponseEntity getAllPlaces(){
        return ResponseEntity.ok().body(placeService.getAllPlaces());
    }

    @GetMapping("/getPlaceReviews/{placeId}")
    public ResponseEntity getPlaceReviews(@PathVariable int placeId){

        return ResponseEntity.ok().body(placeService.getPlaceReviews(placeId));
    }

    @GetMapping("/getPlaceById/{placeId}")
    public ResponseEntity getPlaceById(@PathVariable int placeId){
        return ResponseEntity.ok().body(placeService.getPlaceById(placeId));
    }

    @GetMapping("/getAllHotels")
    public ResponseEntity getAllHotels(){
        return ResponseEntity.ok().body(placeService.getAllHotels());
    }

    @GetMapping("/getAllRestaurant")
    public ResponseEntity getAllRestaurant(){
        return ResponseEntity.ok().body(placeService.getAllRestaurants());
    }

    @PostMapping("savePlace")
    public ResponseEntity savePlace(@RequestBody PlaceDto placeDto){
        return ResponseEntity.ok().body(placeService.savePlace(placeDto));
    }

    @PutMapping("/updatePlaceTitle/{placeId}")
    public ResponseEntity updatePlaceTitle(@PathVariable int placeId, @RequestBody PlaceDto placeDto){
        return ResponseEntity.ok().body(placeService.updatePlaceTitle(placeId,placeDto));
    }

    @PutMapping("/updatePlaceImgUrl/{placeId}")
    public ResponseEntity updatePlaceImgUrl(@PathVariable int placeId,@RequestBody PlaceDto placeDto){
        return ResponseEntity.ok().body(placeService.updatePlaceImgUrl(placeId,placeDto));
    }


    @GetMapping("/searchPlace/{keyword}")
    public ResponseEntity searchPlaces(@PathVariable String keyword){
        return ResponseEntity.ok().body(placeService.searchPlaceByTitleAndDescription(keyword));
    }
}
