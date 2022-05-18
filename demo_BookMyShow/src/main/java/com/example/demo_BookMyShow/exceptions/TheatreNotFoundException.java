package com.example.demo_BookMyShow.exceptions;

public class TheatreNotFoundException extends RuntimeException{
    public TheatreNotFoundException(String ex){
        super(ex);
    }
}
