package com.example.tripadvisorservice.advice.exception;

public class PlaceNotFoundException extends RuntimeException{

    protected PlaceNotFoundException(String message){
        super(message);
    }

}
