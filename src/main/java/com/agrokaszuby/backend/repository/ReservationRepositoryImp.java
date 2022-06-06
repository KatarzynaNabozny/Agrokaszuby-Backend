package com.agrokaszuby.backend.repository;

import com.agrokaszuby.backend.domain.Reservation;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;

@Repository
public class ReservationRepositoryImp {

    ReservationRepository repository;

    public ReservationRepositoryImp(ReservationRepository repository) {
        this.repository = repository;
    }

    public void deleteByStartDateAndEndDateAndEmail(LocalDateTime startDate, LocalDateTime endDate, String email){

        Collection<Reservation> reservations = repository
                .findAllReservationsByCustomerEmailAndStartDateAndEndDate(startDate, endDate, email);

        reservations.stream().forEach(reservation -> repository.deleteById(reservation.getReservationId()));
    };
}
