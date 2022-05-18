package com.example.demo_BookMyShow.services;

import com.example.demo_BookMyShow.Model.*;
import com.example.demo_BookMyShow.enums.SeatStatus;
import com.example.demo_BookMyShow.enums.SeatType;
import lombok.NonNull;
import net.bytebuddy.implementation.bytecode.Throw;

import java.time.LocalDateTime;
import java.util.*;


public class ShowService {

    private TheatreService theatreService;
    private MovieService movieService;

    private Map<String, Show> showMap;

    public ShowService(@NonNull final TheatreService theatreService, @NonNull final MovieService movieService) {
        this.theatreService = theatreService;
        this.movieService = movieService;
        showMap = new HashMap<>();
    }

    public Show createShow(@NonNull final String movieId, @NonNull final String screenId, @NonNull final LocalDateTime startTime, @NonNull final LocalDateTime endTime) {
        Screen screen = theatreService.getScreenById(screenId);
        Movie movie = movieService.getMovieById(movieId);
        String showId = UUID.randomUUID().toString();
        Show show = new Show(showId, startTime, endTime, screen, movie);
        List<ShowSeat> showSeats = getShowSeats(screen, show);
        show.assignShowSeats(showSeats);
        showMap.put(showId, show);
        return  show;

    }

    public List<ShowSeat> getShowSeats(@NonNull final Screen screen, @NonNull final Show show){
        List<Seat> seats = screen.getSeats();
        List<ShowSeat> showSeats = new ArrayList<>();
        for(Seat seat:seats){
            String showSeatId = UUID.randomUUID().toString();
            int price = 0;
            if(seat.getSeatType().equals(SeatType.SILVER)){
                price = 500;
            }else if(seat.getSeatType().equals(SeatType.GOLD)){
                price = 800;
            }else if(seat.getSeatType().equals(SeatType.PLATINUM)){
                price = 1000;
            }else if(seat.getSeatType().equals(SeatType.SOFA)){
                price = 1200;
            } else if(seat.getSeatType().equals(SeatType.RECLINER)){
                price = 1500;
            }
            ShowSeat showSeat = new ShowSeat(showSeatId, seat, show, SeatStatus.AVAILABLE, price );
            showSeats.add(showSeat);
        }
        return showSeats;
    }

    public List<ShowSeat> getShowSeatsFromIds(@NonNull final Show show, @NonNull final List<String> showSeatIds){
        List<ShowSeat> showSeats = new ArrayList<>();

        for(ShowSeat showSeat:show.getShowseats()){
            if(showSeatIds.contains(showSeat.getShowSeatId())){
                showSeats.add(showSeat);
            }
        }
        return showSeats;
    }

    public Show getShow(@NonNull final String showId) {
        if(!showMap.containsKey(showId)){
            throw new RuntimeException("Show not available");
        }else{
            return showMap.get(showId);
        }

    }

    public boolean checkIfSeatsCanBeBooked(@NonNull final List<String> seatsToBook, @NonNull final String showId) {

        Show show = showMap.get(showId);
        for(ShowSeat showSeat:show.getShowseats()){
            if(seatsToBook.contains(showSeat.getShowSeatId())){
                if(showSeat.getSeatStatus().equals(SeatStatus.BOOKED))
                    return false;
            }
        }
        return true;
    }
}
