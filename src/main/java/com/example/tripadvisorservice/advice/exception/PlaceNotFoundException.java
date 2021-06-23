package com.example.tripadvisorservice.advice.exception;

public class PlaceNotFoundException extends RuntimeException{

    public PlaceNotFoundException(String message){
        super(message);
    }

}
