package com.agrokaszuby.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CurrencyExchangeNotFoundException.class)
    public ResponseEntity<Object> handleCurrencyExchangeNotFoundException(CurrencyExchangeNotFoundException exception) {
        return new ResponseEntity<>("Currency exchange not exists", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ReservationNotFoundException.class)
    public ResponseEntity<Object> handleReservationNotFoundException(ReservationNotFoundException exception) {
        return new ResponseEntity<>("Reservation not exists", HttpStatus.BAD_REQUEST);
    }
}
