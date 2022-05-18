package com.example.demo_BookMyShow.services;

import com.example.demo_BookMyShow.Model.Movie;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MovieService {

    private Map<String, Movie> movieMap;

    public MovieService(){
        this.movieMap = new HashMap<>();
    }

    public Movie createMovie(@NonNull final String movieName) {
        String movieId = UUID.randomUUID().toString();
        Movie movie = new Movie(movieId, movieName);
        movieMap.put(movieId, movie);
        return movie;
    }

    public Movie getMovieById(@NonNull final String movieId) {
        //TODO - Add exceptions
        return movieMap.get(movieId);
    }
}
