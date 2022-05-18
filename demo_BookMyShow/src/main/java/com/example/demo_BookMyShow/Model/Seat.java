package com.example.demo_BookMyShow.Model;

import com.example.demo_BookMyShow.enums.SeatType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Seat {

    private String seatId;
    private SeatType seatType;
    private Screen screen;

    public Seat(String seatId, SeatType seatType) {
        this.seatId = seatId;
        this.seatType = seatType;
    }

    public void addScreenToSeat(Screen screen){
        this.screen = screen;
    }
}
