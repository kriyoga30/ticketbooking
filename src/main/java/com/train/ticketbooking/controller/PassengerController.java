package com.train.ticketbooking.controller;

import com.train.ticketbooking.entities.Passenger;
import com.train.ticketbooking.entities.Seat;
import com.train.ticketbooking.entities.Ticket;
import com.train.ticketbooking.exception.SeatNotFoundException;
import com.train.ticketbooking.exception.UserNotFoundException;
import com.train.ticketbooking.model.SeatChangeRequest;
import com.train.ticketbooking.model.User;
import com.train.ticketbooking.repositories.PassengerRepository;
import com.train.ticketbooking.repositories.SeatRepository;
import com.train.ticketbooking.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class PassengerController{
    @Autowired
    PassengerRepository passengerRepository;
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping("/users")
    List<Passenger> all() {
        return passengerRepository.findAll();
    }

    @PostMapping("/user")
    Passenger createNew(@RequestBody Passenger newUser) {
        return passengerRepository.save(newUser);
    }

    @DeleteMapping("/user")
    void delete(@RequestBody User oldUser) {
        List<Seat> seat = seatRepository.findByPassenger_FirstNameLikeAndPassenger_LastNameLike(oldUser.getFirstName(), oldUser.getLastName());
        if (seat != null && seat.get(0) != null) {
            Seat seat1 = seat.get(0);
            Long passengetId = seat1.getPassenger().getPassengerId();
            seat1.setPassenger(null);
            seatRepository.save(seat1);
            passengerRepository.deleteById(passengetId);
        } else {
            throw new UserNotFoundException(oldUser.getFirstName(), oldUser.getLastName());
        }

    }

    @PutMapping("/user")
    Ticket changeSeat(@RequestBody SeatChangeRequest seatChangeRequest) {
        Optional<Ticket> ticket = ticketRepository.findBySeat_Passenger_FirstNameAndSeat_Passenger_LastName(seatChangeRequest.getFirstName(), seatChangeRequest.getLastName());
        List<Seat> seat = seatRepository.findBySectionLikeAndNumberLike(seatChangeRequest.getNewSeatSection(), seatChangeRequest.getNewSeatNumber());
        if (ticket.isPresent()) {
            if (seat != null && seat.get(0) != null) {
                Ticket ticket1 = ticket.get();
                Seat seat1 = seat.get(0);
                seat1.setPassenger(ticket1.getSeat().getPassenger());
                Seat oldSeat = ticket1.getSeat();
                oldSeat.setPassenger(null);
                ticket1.setSeat(seat1);
                seatRepository.save(oldSeat);
                return ticketRepository.save(ticket1);
            } else {
                throw new SeatNotFoundException();
            }
        } else {
            throw new UserNotFoundException(seatChangeRequest.getFirstName(), seatChangeRequest.getLastName());
        }


    }


}