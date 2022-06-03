package com.agrokaszuby.backend.service;

import com.agrokaszuby.backend.domain.Reservation;
import com.agrokaszuby.backend.exception.ReservationNotFoundException;
import com.agrokaszuby.backend.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationDBService {
    
    private final ReservationRepository repository;

    public List<Reservation> getAllReservations() {
        return repository.findAll();
    }

    public Reservation getReservation(final Long reservationId) throws ReservationNotFoundException {
        return repository.findById(reservationId).orElseThrow(ReservationNotFoundException::new);
    }

    public Reservation saveReservation(final Reservation reservation) {
        return repository.save(reservation);
    }

    public void deleteReservation(final Long reservationId) throws ReservationNotFoundException {
        try {
            repository.deleteById(reservationId);
        } catch (Exception e) {
            throw new ReservationNotFoundException();
        }
    }
    
}