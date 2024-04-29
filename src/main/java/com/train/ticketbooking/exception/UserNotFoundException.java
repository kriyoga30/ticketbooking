package com.train.ticketbooking.exception;

public class UserNotFoundException extends RuntimeException{
    private Long id;

    public UserNotFoundException(String firstName, String lastName) {
        super("Could not find user " + firstName + " " + lastName);
    }
}
