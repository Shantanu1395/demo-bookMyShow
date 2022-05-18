package com.example.demo_BookMyShow.Model;

import com.example.demo_BookMyShow.enums.SeatType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SeatIdentifier {
    int row;
    int col;
    SeatType seatType;
}
