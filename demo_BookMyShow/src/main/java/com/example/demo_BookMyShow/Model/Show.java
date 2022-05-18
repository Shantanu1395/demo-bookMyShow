package com.example.demo_BookMyShow.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Show {

    private String showId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private Screen screen;
    private Movie movie;

    private List<ShowSeat> showseats;

    public Show(String showId, LocalDateTime startTime, LocalDateTime endTime, Screen screen, Movie movie) {
        this.showId = showId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.screen = screen;
        this.movie = movie;
        this.showseats = new ArrayList<>();
    }

    public void assignShowSeats(List<ShowSeat> showSeats) {
        this.showseats = showSeats;
    }
}
