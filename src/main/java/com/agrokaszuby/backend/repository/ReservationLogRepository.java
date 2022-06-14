package com.agrokaszuby.backend.repository;

import com.agrokaszuby.backend.domain.ReservationLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ReservationLogRepository extends CrudRepository<ReservationLog, Long> {

    @Override
    List<ReservationLog> findAll();

    @Override
    Optional<ReservationLog> findById(Long reservationLogId);

    @Override
    ReservationLog save(ReservationLog reservationLog);

    @Override
    void deleteById(Long reservationLogId);

}