package com.example.demo_BookMyShow.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class Movie {

    private String movieId;
    private String movieName;

    //Commenting metadata info
//    private String description;
//    private String language;
//    private String genre;
//    private String cast;
//    private int runTime;
}
