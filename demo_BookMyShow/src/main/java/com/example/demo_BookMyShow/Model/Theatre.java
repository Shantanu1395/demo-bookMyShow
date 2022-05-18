package com.example.demo_BookMyShow.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Theatre {

    private String theatreId;
    private String theatreName;

    //Uncomment for adding metadata info
    //private String theatreLocation;

    private City city;

    private List<Screen> screens;

    public Theatre(String theatreId, String theatreName, City city) {
        this.theatreId = theatreId;
        this.theatreName = theatreName;
        this.city = city;
        this.screens = new ArrayList<>();
    }

    public void addScreenToTheatre(Screen screen){
        this.screens.add(screen);
    }

}
