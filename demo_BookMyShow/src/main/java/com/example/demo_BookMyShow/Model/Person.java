package com.example.demo_BookMyShow.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Person {

    private String personId;
    //Add enum here
    //private PersonType personType;
    private String personName;

//    private String password;
//    private String email;
//    private String phoneNumber;
//    private String address;

    private List<Booking> bookings;

    public Person(String personId, String personName) {
        this.personId = personId;
        this.personName = personName;
        this.bookings = new ArrayList<>();
    }

    public void addBookingToPerson(Booking booking){
        this.bookings.add(booking);
    }

}
