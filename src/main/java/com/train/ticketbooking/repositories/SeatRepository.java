package com.train.ticketbooking.repositories;

import com.train.ticketbooking.entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

    List<Seat> findBySectionLike(String section);

    List<Seat> findFirst1ByPassenger_PassengerIdIsNullOrderBySeatIdAsc();

    List<Seat> findBySectionLikeAndNumberLike(String section, String number);

    List<Seat> findByPassenger_FirstNameLikeAndPassenger_LastNameLike(String firstName, String lastName);
}
