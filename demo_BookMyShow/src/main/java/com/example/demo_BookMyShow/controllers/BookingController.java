package com.example.demo_BookMyShow.controllers;

import com.example.demo_BookMyShow.services.BookingService;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.List;

@AllArgsConstructor
public class BookingController {

    private BookingService bookingService;

    public String createBooking(@NonNull final String customerId, @NonNull final List<String> seatsToBook, @NonNull final String showId) {
        return bookingService.createBooking(customerId, seatsToBook, showId).getBookingId();
    }
}
