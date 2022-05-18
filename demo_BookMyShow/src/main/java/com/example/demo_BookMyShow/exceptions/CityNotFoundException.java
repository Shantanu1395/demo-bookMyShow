package com.example.demo_BookMyShow.exceptions;

public class CityNotFoundException extends RuntimeException{
    public CityNotFoundException(String ex){
        super(ex);
    }
}
