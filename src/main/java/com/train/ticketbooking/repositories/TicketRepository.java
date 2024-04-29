package com.train.ticketbooking.repositories;

import com.train.ticketbooking.entities.Seat;
import com.train.ticketbooking.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Optional<Ticket> findBySeat_Passenger_FirstNameAndSeat_Passenger_LastName(String firstName, String lastName);
}
