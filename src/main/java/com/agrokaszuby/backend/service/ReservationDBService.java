package com.agrokaszuby.backend.service;

import com.agrokaszuby.backend.domain.Reservation;
import com.agrokaszuby.backend.exception.ReservationNotFoundException;
import com.agrokaszuby.backend.repository.ReservationRepository;
import com.agrokaszuby.backend.repository.ReservationRepositoryImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationDBService {

    private final ReservationRepository repository;
    private final ReservationRepositoryImp repositoryImp;

    public List<Reservation> getAllReservations() {
        return repository.findAll();
    }

    public List<Reservation> getAllReservationsForClientByEmail(String email) {
        return repository.findAllReservationsByCustomerEmail(email).stream().toList();
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

    public void deleteReservationByEmailAndStartAndEndDate(final String email,
                                                           LocalDateTime startDate,
                                                           LocalDateTime endDate) throws ReservationNotFoundException {
        try {
            repositoryImp.deleteByStartDateAndEndDateAndEmail(startDate, endDate, email);
        } catch (Exception e) {
            throw new ReservationNotFoundException();
        }
    }

}