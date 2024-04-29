package com.train.ticketbooking.exception;

public class TicketNotFoundException extends RuntimeException{
    private Long id;

    public TicketNotFoundException(Long id) {
        super("Could not find ticket " + id);
    }
}
