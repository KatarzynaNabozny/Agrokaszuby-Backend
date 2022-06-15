package com.agrokaszuby.backend.service;

import com.agrokaszuby.backend.domain.ReservationLog;
import com.agrokaszuby.backend.exception.ReservationLogNotFoundException;
import com.agrokaszuby.backend.repository.ReservationLogRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReservationLogDBServiceTest {

    @InjectMocks
    ReservationLogDBService dbService;
    @Mock
    ReservationLogRepository repository;

    @Test
    void getAllReservationLogs() {
        //given
        ReservationLog reservationLog = new ReservationLog();
        List<ReservationLog> reservationLogList = new ArrayList<>();
        reservationLogList.add(reservationLog);
        //when
        List<ReservationLog> allReservationLogs = dbService.getAllReservationLogs();
        //then
        assertEquals(0, allReservationLogs.size());

    }

    @Test
    void getReservationLog() throws ReservationLogNotFoundException {
        //given
        ReservationLog reservationLog = new ReservationLog(1L, "fromName", "email",
                Boolean.TRUE, LocalDateTime.now());

        //when
        when(repository.findById(1L)).thenReturn(java.util.Optional.of(reservationLog));
        ReservationLog actualReservationLog = dbService.getReservationLog(1L);
        //then
        assertEquals(reservationLog, actualReservationLog);

    }
}