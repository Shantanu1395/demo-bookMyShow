package com.example.demo_BookMyShow.controllers;

import com.example.demo_BookMyShow.Model.Movie;
import com.example.demo_BookMyShow.services.MovieService;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.UUID;

@AllArgsConstructor
public class MovieController {

    private MovieService movieService;

    public String createMovie(@NonNull final String movieName){
        return movieService.createMovie(movieName).getMovieId();
    }

}
