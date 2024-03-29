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

    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<Object> handleCommentNotFoundException(CommentNotFoundException exception) {
        return new ResponseEntity<>("Comment not exists", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(QuestionNotFoundException.class)
    public ResponseEntity<Object> handleQuestionNotFoundException(QuestionNotFoundException exception) {
        return new ResponseEntity<>("Question not exists", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CommentLogNotFoundException.class)
    public ResponseEntity<Object> handleCommentLogNotFoundException(CommentLogNotFoundException exception) {
        return new ResponseEntity<>("Comment log not exists", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(QuestionLogNotFoundException.class)
    public ResponseEntity<Object> handleQuestionLogNotFoundException(QuestionLogNotFoundException exception) {
        return new ResponseEntity<>("Question log not exists", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ReservationLogNotFoundException.class)
    public ResponseEntity<Object> handleReservationLogNotFoundException(ReservationLogNotFoundException exception) {
        return new ResponseEntity<>("Reservation log not exists", HttpStatus.BAD_REQUEST);
    }
}
