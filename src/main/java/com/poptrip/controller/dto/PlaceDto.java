package com.poptrip.controller.dto;

import com.poptrip.entity.enums.PlaceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceDto {


    private String placeTitle;
    private String placeDescription;
    private PlaceType placeType;
    private String placeImgUrl;

}
