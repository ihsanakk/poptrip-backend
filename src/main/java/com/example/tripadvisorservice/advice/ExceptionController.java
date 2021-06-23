package com.example.tripadvisorservice.advice;

import com.example.tripadvisorservice.advice.exception.PlaceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(PlaceNotFoundException.class)
    protected ResponseEntity<?> handlePlaceNotFoundException(PlaceNotFoundException placeNotFoundException){
        return new ResponseEntity<>(placeNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

}
