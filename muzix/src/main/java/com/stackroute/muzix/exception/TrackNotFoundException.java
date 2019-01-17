package com.stackroute.muzix.exception;

public class TrackNotFoundException extends Exception {


    private String message2;


    public TrackNotFoundException(){};

    public TrackNotFoundException(String message2){
        super(message2);
        this.message2=message2;

    }

}
