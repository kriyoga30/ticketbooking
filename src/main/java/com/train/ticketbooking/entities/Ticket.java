package com.train.ticketbooking.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Long ticketId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="seat_id")
    private Seat seat;

    private Double price;

    private String destination;

    private String startingPoint;
}
