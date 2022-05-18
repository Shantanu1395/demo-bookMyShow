package com.example.demo_BookMyShow;

import com.example.demo_BookMyShow.Model.*;
import com.example.demo_BookMyShow.controllers.*;
import com.example.demo_BookMyShow.enums.ScreenType;
import com.example.demo_BookMyShow.enums.SeatStatus;
import com.example.demo_BookMyShow.enums.SeatType;
import com.example.demo_BookMyShow.services.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BaseTestcase {

    TheatreController theatreController;
    ShowController showController;
    MovieController movieController;
    PersonController personController;
    BookingController bookingController;

    public void setupControllers(){
        TheatreService theatreService = new TheatreService();
        MovieService movieService = new MovieService();
        ShowService showService = new ShowService(theatreService, movieService);
        PersonService personService = new PersonService();
        BookingService bookingService = new BookingService(personService, showService);

        theatreController = new TheatreController(theatreService);
        movieController = new MovieController(movieService);
        showController = new ShowController(showService);
        personController = new PersonController(personService);
        bookingController = new BookingController(bookingService);
    }

    @Test
    public void testInfra(){

        setupControllers();

        String cityId = theatreController.createCity("Noida");

        String theatreId = theatreController.createTheatre("SRS", cityId);

        List<SeatIdentifier> seatIdentifierList = new ArrayList<>();
        seatIdentifierList.add(new SeatIdentifier(3, 20, SeatType.SILVER));
        seatIdentifierList.add(new SeatIdentifier(3, 10, SeatType.GOLD));
        seatIdentifierList.add(new SeatIdentifier(2, 5, SeatType.PLATINUM));

        String screenId1 = theatreController.createScreen("Screen 1", ScreenType.TWOD, theatreId, seatIdentifierList);

        seatIdentifierList = new ArrayList<>();
        seatIdentifierList.add(new SeatIdentifier(3, 10, SeatType.SILVER));
        seatIdentifierList.add(new SeatIdentifier(3, 5, SeatType.GOLD));
        seatIdentifierList.add(new SeatIdentifier(2, 5, SeatType.SOFA));
        String screenId2 = theatreController.createScreen("Screen 2", ScreenType.THREED, theatreId, seatIdentifierList);

        City city = theatreController.getCities().get(0);

        Theatre theatre = city.getTheatres().get(0);
        Assertions.assertEquals(theatre.getScreens().get(0).getSeats().size(), 100);
        Assertions.assertEquals(theatre.getScreens().get(1).getSeats().size(), 55);


        //Creating movie & show
        String movieId = movieController.createMovie("Singham100");
        String showId = showController.createShow("AfernoonShow", movieId, screenId1, LocalDateTime.now(), LocalDateTime.now().plusMinutes(120));

        Show show = showController.getShow(showId);

        Assertions.assertEquals(show.getShowseats().stream().filter(showSeat -> showSeat.getSeatStatus().equals(SeatStatus.AVAILABLE)).collect(Collectors.toList()).size(), 100);

        List<String> seatsToBook = new ArrayList<>();
        seatsToBook.add(show.getShowseats().get(0).getShowSeatId());
        seatsToBook.add(show.getShowseats().get(31).getShowSeatId());
        String customer1 = personController.createPerson("Ajay");
        String customer2 = personController.createPerson("Shantanu");
        String booking1 = bookingController.createBooking(customer1, seatsToBook, showId);

        Assertions.assertEquals(show.getShowseats().get(0).getSeatStatus(), SeatStatus.BOOKED);
        Assertions.assertEquals(show.getShowseats().get(1).getSeatStatus(), SeatStatus.AVAILABLE);
        Assertions.assertEquals(show.getShowseats().get(31).getSeatStatus(), SeatStatus.BOOKED);
        Assertions.assertEquals(showController.checkIfSeatsCanBeBooked(seatsToBook, showId), false);

        //Book tickets even if it is already booked
        //Runtime exception
        seatsToBook = new ArrayList<>();
        seatsToBook.add(show.getShowseats().get(0).getShowSeatId());
        seatsToBook.add(show.getShowseats().get(32).getShowSeatId());
        try{
            String booking2 = bookingController.createBooking(customer2, seatsToBook, showId);
        }catch (RuntimeException runtimeException){
            Assertions.assertEquals("Can't do the booking, seat already booked", runtimeException.getMessage());
        }

    }

}


