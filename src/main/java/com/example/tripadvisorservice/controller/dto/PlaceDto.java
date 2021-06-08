package com.example.tripadvisorservice.controller.dto;

import com.example.tripadvisorservice.entity.enums.PlaceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceDto {


    private String placeTitle;
    private String placeDescription;
    private PlaceType placeType;
    private String placeImgUrl;

}
