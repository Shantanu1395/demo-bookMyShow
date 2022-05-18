package com.example.demo_BookMyShow.controllers;

import com.example.demo_BookMyShow.Model.Show;
import com.example.demo_BookMyShow.services.MovieService;
import com.example.demo_BookMyShow.services.ShowService;
import com.example.demo_BookMyShow.services.TheatreService;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
public class ShowController {

    private ShowService showService;

    public String createShow(@NonNull final String showName, @NonNull final String movieId, @NonNull final String screenId, @NonNull final LocalDateTime startTime, @NonNull final LocalDateTime endTime){
        return showService.createShow(movieId, screenId, startTime, endTime).getShowId();
    }

    public Show getShow(@NonNull final String showId) {
        return showService.getShow(showId);
    }

    public boolean checkIfSeatsCanBeBooked(@NonNull final List<String> seatsToBook, @NonNull final String showId) {
        return showService.checkIfSeatsCanBeBooked(seatsToBook, showId);
    }
}
