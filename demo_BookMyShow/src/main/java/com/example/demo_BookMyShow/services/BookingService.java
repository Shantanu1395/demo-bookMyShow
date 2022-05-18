package com.example.demo_BookMyShow.services;

import com.example.demo_BookMyShow.Model.Booking;
import com.example.demo_BookMyShow.Model.Person;
import com.example.demo_BookMyShow.Model.Show;
import com.example.demo_BookMyShow.Model.ShowSeat;
import com.example.demo_BookMyShow.enums.SeatStatus;
import lombok.NonNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class BookingService {

    private Map<String, Booking> bookingMap;
    private PersonService personService;
    private ShowService showService;

    public BookingService(@NonNull final PersonService personService, @NonNull final ShowService showService) {
        this.personService = personService;
        this.showService = showService;
        this.bookingMap = new HashMap<>();
    }

    public Booking createBooking(@NonNull final String customerId, @NonNull final List<String> seatsToBook, @NonNull final String showId) {

        if(!showService.checkIfSeatsCanBeBooked(seatsToBook, showId)) throw new RuntimeException("Can't do the booking, seat already booked");

        String bookingId = UUID.randomUUID().toString();
        Person customer = personService.getPersonById(customerId);
        Show show = showService.getShow(showId);
        List<ShowSeat> showSeats = showService.getShowSeatsFromIds(show, seatsToBook);
        markShowSeatsAsBooked(showSeats);
        Booking booking = new Booking(bookingId, customer, showSeats, show);
        bookingMap.put(bookingId, booking);
        customer.addBookingToPerson(booking);
        return booking;
    }

    private void markShowSeatsAsBooked(@NonNull final List<ShowSeat> showSeats) {
        for (int i = 0, showSeatsSize = showSeats.size(); i < showSeatsSize; i++) {
            ShowSeat showSeat = showSeats.get(i);
            showSeat.setShowSeatStatus(SeatStatus.BOOKED);
        }
    }
}
