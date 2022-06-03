package com.agrokaszuby.backend.repository;

import com.agrokaszuby.backend.domain.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    @Override
    List<Reservation> findAll();

    @Override
    Optional<Reservation> findById(Long reservationId);

    @Override
    Reservation save(Reservation reservation);

    @Override
    void deleteById(Long reservationId);
}
