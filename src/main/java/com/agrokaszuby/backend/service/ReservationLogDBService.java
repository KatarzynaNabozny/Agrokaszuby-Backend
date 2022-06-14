package com.agrokaszuby.backend.service;

import com.agrokaszuby.backend.domain.ReservationLog;
import com.agrokaszuby.backend.exception.ReservationLogNotFoundException;
import com.agrokaszuby.backend.repository.ReservationLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class ReservationLogDBService {

    private final ReservationLogRepository repository;

    public List<ReservationLog> getAllReservationLogs() {
        return repository.findAll();
    }

    public ReservationLog getReservationLog(final Long reservationLogId) throws ReservationLogNotFoundException {
        return repository.findById(reservationLogId).orElseThrow(ReservationLogNotFoundException::new);
    }

    public ReservationLog saveReservationLog(final ReservationLog reservationLog) {
        return repository.save(reservationLog);
    }

    public void deleteReservationLog(final Long reservationLogId) throws ReservationLogNotFoundException {
        try {
            repository.deleteById(reservationLogId);
        } catch (Exception e) {
            throw new ReservationLogNotFoundException();
        }
    }

}