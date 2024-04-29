package com.train.ticketbooking.exception;

public class SeatNotFoundException extends RuntimeException{
    private Long id;

    public SeatNotFoundException() {
        super("Could not find given seat ");
    }
}
