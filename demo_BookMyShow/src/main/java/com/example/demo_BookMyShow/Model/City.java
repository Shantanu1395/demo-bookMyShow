package com.example.demo_BookMyShow.Model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class City {

    private String cityId;
    private String cityName;
    private List<Theatre> theatres;

    public City(String cityId, String cityName) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.theatres = new ArrayList<>();
    }

    public void addTheatreToCity(Theatre theatre){
        this.theatres.add(theatre);
    }

}
