package com.train.ticketbooking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Receipt {

    private Long id;

    private String from;

    private String to;

    private User user;

    private Double price;

    private String section;

    private String seatNumber;
}
