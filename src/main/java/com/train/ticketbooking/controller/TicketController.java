package com.train.ticketbooking.controller;

import com.train.ticketbooking.entities.Seat;
import com.train.ticketbooking.entities.Ticket;
import com.train.ticketbooking.entities.Passenger;
import com.train.ticketbooking.exception.SeatNotFoundException;
import com.train.ticketbooking.model.Receipt;
import com.train.ticketbooking.model.TicketDetails;
import com.train.ticketbooking.model.User;
import com.train.ticketbooking.repositories.SeatRepository;
import com.train.ticketbooking.repositories.TicketRepository;
import com.train.ticketbooking.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
public class TicketController {
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private SeatController seatController;
    @Autowired
    private SeatRepository seatRepository;

    @GetMapping("/tickets")
    List<Ticket> all() {
        return ticketRepository.findAll();
    }

    @PostMapping("/ticket")
    Receipt createNew(@RequestBody TicketDetails newTicket) {
        List<Passenger> passengerList =  new ArrayList<Passenger>();

        List<Seat> seat =  seatRepository.findFirst1ByPassenger_PassengerIdIsNullOrderBySeatIdAsc();

        if (seat.get(0) != null) {
            Seat seat1 = seat.get(0) ;
            seat1.setPassenger(Passenger.builder()
                    .firstName(newTicket.getUser().getFirstName())
                    .lastName(newTicket.getUser().getLastName())
                    .emailId(newTicket.getUser().getEmailId()).build());

            Ticket ticket = ticketRepository.save(Ticket.builder()
                    .seat(seat1)
                    .price((double) 5)
                    .destination(newTicket.getTo()).startingPoint(newTicket.getFrom()).build());

            return Receipt.builder().id(ticket.getTicketId()).from(ticket.getStartingPoint()).to(ticket.getDestination()
            ).user(User.builder().firstName(ticket.getSeat().getPassenger().getFirstName())
                            .lastName(ticket.getSeat().getPassenger().getLastName())
                            .emailId(ticket.getSeat().getPassenger().getEmailId()).build())
                    .section(ticket.getSeat().getSection())
                    .seatNumber(ticket.getSeat().getNumber())
                    .price(ticket.getPrice()).build();
        } else {
            throw new SeatNotFoundException();
        }








        //return ticketRepository.saveAll(newTicket);
    }

    @GetMapping("/tickets/{id}")
    Optional<Ticket> getTicket(@PathVariable Long id) {
        return ticketRepository.findById(id);
    }

}