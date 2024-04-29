package com.train.ticketbooking.controller;

import com.train.ticketbooking.entities.Seat;
import com.train.ticketbooking.repositories.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class SeatController {
    @Autowired
    SeatRepository seatRepository;

    @GetMapping("/seats/{section}")
    List<Seat> findBySection(@PathVariable String section) {
        return seatRepository.findBySectionLike(section);
    }
}