package com.train.ticketbooking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String userNotFoundHandler(UserNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(SeatNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String seatNotFoundHandler(SeatNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(TicketNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String ticketNotFoundHandler(SeatNotFoundException ex) {
        return ex.getMessage();
    }



}
