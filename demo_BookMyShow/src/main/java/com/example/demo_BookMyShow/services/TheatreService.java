package com.example.demo_BookMyShow.services;

import com.example.demo_BookMyShow.Model.*;
import com.example.demo_BookMyShow.enums.ScreenType;
import com.example.demo_BookMyShow.enums.SeatType;
import com.example.demo_BookMyShow.exceptions.CityNotFoundException;
import com.example.demo_BookMyShow.exceptions.ScreenNotFoundException;
import com.example.demo_BookMyShow.exceptions.TheatreNotFoundException;
import lombok.NonNull;

import java.util.*;

public class TheatreService {

    private Map<String, City> cities;
    private Map<String, Theatre> theatres;
    private Map<String, Screen> screens;

    public TheatreService() {
        this.cities = new HashMap();
        this.theatres = new HashMap();
        this.screens = new HashMap();
    }

    public String createCity(@NonNull final String cityName) {
        String cityId = UUID.randomUUID().toString();
        City city = new City(cityId, cityName);
        cities.put(cityId, city);
        return cityId;
    }

    public String createTheatre(@NonNull final String theatreName,@NonNull final String cityId){

        if(!cities.containsKey(cityId)) throw new CityNotFoundException("City not found in datastore");

        City city = cities.get(cityId);
        String theatreId = UUID.randomUUID().toString();
        Theatre theatre = new Theatre(theatreId, theatreName, city);
        city.addTheatreToCity(theatre);
        theatres.put(theatreId, theatre);
        return theatreId;
    }

    public Screen createScreen(@NonNull final String screenName, @NonNull final ScreenType screenType, @NonNull final String theatreId, @NonNull final List<SeatIdentifier> seatIdentifiers){

        if(!theatres.containsKey(theatreId)) throw new TheatreNotFoundException("Theatre not found in datastore");

        //Creating seats here
        List<Seat> allSeats = new ArrayList<>();
        for(SeatIdentifier seatIdentifier:seatIdentifiers){
            allSeats.addAll(createSeats(seatIdentifier.getRow(), seatIdentifier.getCol(), seatIdentifier.getSeatType()));
        }

        String screenId = UUID.randomUUID().toString();
        Theatre theatre = theatres.get(theatreId);
        Screen screen = new Screen(screenId, screenName, screenType, theatre, allSeats);

        for(Seat seat:allSeats){
            seat.addScreenToSeat(screen);
        }

        theatre.addScreenToTheatre(screen);

        screens.put(screenId, screen);

        return screen;

    }

    public List<Seat> createSeats(@NonNull final int row, @NonNull final int column, @NonNull final SeatType seatType){
        List<Seat> seats = new ArrayList<>();
        for(int i = 1; i <= row; i++){
            for(int j = 1; j <= column; j++){
                String seatId = UUID.randomUUID().toString();
                seats.add(new Seat(seatId, seatType));
            }
        }
        return seats;
    }

    public City getCityById(@NonNull final String cityId) {
        return this.cities.get(cityId);
    }

    public List<City> getCities(){
        return cities.values().stream().toList();
    }

    public Screen getScreenById(@NonNull final String screenId) {
        if(!screens.containsKey(screenId)) throw new ScreenNotFoundException("Screen not found in datastore");
        return screens.get(screenId);
    }
}
