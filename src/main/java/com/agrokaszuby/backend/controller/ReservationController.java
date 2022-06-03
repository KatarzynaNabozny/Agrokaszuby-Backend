package com.agrokaszuby.backend.controller;

import com.agrokaszuby.backend.domain.Reservation;
import com.agrokaszuby.backend.domain.dto.ReservationDTO;
import com.agrokaszuby.backend.exception.ReservationNotFoundException;
import com.agrokaszuby.backend.mapper.ReservationMapper;
import com.agrokaszuby.backend.service.ReservationDBService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/agrokaszuby/backend")
public class ReservationController {
    
    private final ReservationDBService service;
    private final ReservationMapper mapper;

    @GetMapping
    public ResponseEntity<List<ReservationDTO>> getReservations() {
        List<Reservation> reservations = service.getAllReservations();
        return ResponseEntity.ok(mapper.mapToReservationDtoList(reservations));
    }

    @GetMapping(value = "/{reservationId}")
    public ResponseEntity<ReservationDTO> getReservation(@PathVariable Long reservationId) throws ReservationNotFoundException {
        return ResponseEntity.ok(mapper.mapToReservationDTO(
                service.getReservation(reservationId)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createReservation(@RequestBody ReservationDTO reservationDTO) {
        Reservation reservation = mapper.mapToReservation(reservationDTO);
        service.saveReservation(reservation);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<ReservationDTO> updateReservation(@RequestBody ReservationDTO reservationDto) {
        Reservation reservation = mapper.mapToReservation(reservationDto);
        Reservation savedReservation = service.saveReservation(reservation);
        return ResponseEntity.ok(mapper.mapToReservationDTO(savedReservation));
    }

    @DeleteMapping(value = "/{reservationId}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long reservationId) throws ReservationNotFoundException {
        service.deleteReservation(reservationId);
        return ResponseEntity.ok().build();
    }
}
