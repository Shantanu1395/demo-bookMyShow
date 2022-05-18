package com.example.demo_BookMyShow.controllers;

import com.example.demo_BookMyShow.Model.City;
import com.example.demo_BookMyShow.Model.SeatIdentifier;
import com.example.demo_BookMyShow.enums.ScreenType;
import com.example.demo_BookMyShow.services.TheatreService;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.List;

@AllArgsConstructor
public class TheatreController {

    private TheatreService theatreService;

    public String createCity(@NonNull final String cityName){
        String cityID = theatreService.createCity(cityName);
        return cityID;
    }

    public String createTheatre(@NonNull final String theatreName, @NonNull final String cityId){
        String theatreID = theatreService.createTheatre(theatreName, cityId);
        return theatreID;
    }

    public String createScreen(@NonNull final String screenName, @NonNull final ScreenType screenType, @NonNull final String theatreId, @NonNull final List<SeatIdentifier> seats){
        return theatreService.createScreen(screenName, screenType, theatreId, seats).getScreenId();
    }

    public List<City> getCities(){
        return theatreService.getCities();
    }

}
