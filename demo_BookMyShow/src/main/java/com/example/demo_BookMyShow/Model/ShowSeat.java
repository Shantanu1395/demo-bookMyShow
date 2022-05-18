package com.example.demo_BookMyShow.Model;

import com.example.demo_BookMyShow.enums.SeatStatus;
import com.example.demo_BookMyShow.enums.SeatType;
import lombok.Getter;


@Getter
public class ShowSeat extends Seat {

    private String showSeatId;
    private SeatStatus seatStatus;
    private float price;
    private Show show;

    public ShowSeat(String seatId, SeatType seatType, String showId, SeatStatus seatStatus, float price) {
        super(seatId, seatType);
        this.showSeatId = showId;
        this.seatStatus = seatStatus;
        this.price = price;
    }

    public ShowSeat(String showSeatId, Seat seat, Show show, SeatStatus seatStatus, float price) {
        super(seat.getSeatId(), seat.getSeatType());
        this.showSeatId = showSeatId;
        this.seatStatus = seatStatus;
        this.show = show;
        this.price = price;
    }

    public void assignShowToShowSeat(Show show){
        this.show = show;
    }

    public void setShowSeatStatus(SeatStatus seatStatus){
        this.seatStatus = seatStatus;
    }

}
