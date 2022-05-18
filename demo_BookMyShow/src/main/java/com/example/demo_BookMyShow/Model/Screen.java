package com.example.demo_BookMyShow.Model;

import com.example.demo_BookMyShow.enums.ScreenType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.internal.SessionOwnerBehavior;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Screen {

    private String screenId;
    private String screenName;
    private ScreenType screenType;

    private Theatre theatre;

    private List<Show> shows;
    private List<Seat> seats;

    public Screen(String screenId, String screenName, ScreenType screenType, Theatre theatre, List<Seat> seats) {
        this.screenId = screenId;
        this.screenName = screenName;
        this.screenType = screenType;
        this.theatre = theatre;
        this.seats = seats;
        this.shows = new ArrayList<>();
    }

    public void addShowToScreen(Show show){
        this.shows.add(show);
    }

}
