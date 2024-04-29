package com.train.ticketbooking.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SeatChangeRequest {
    private String firstName;

    private String lastName;

    private String oldSeatSection;

    private String oldSeatNumber;

    private String newSeatSection;

    private String newSeatNumber;
}
