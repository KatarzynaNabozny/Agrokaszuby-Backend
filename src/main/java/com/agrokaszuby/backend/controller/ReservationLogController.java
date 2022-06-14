package com.agrokaszuby.backend.controller;

import com.agrokaszuby.backend.domain.ReservationLog;
import com.agrokaszuby.backend.domain.dto.ReservationLogDTO;
import com.agrokaszuby.backend.exception.ReservationLogNotFoundException;
import com.agrokaszuby.backend.mapper.ReservationLogMapper;
import com.agrokaszuby.backend.service.ReservationLogDBService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/agrokaszuby/backend/reservation_log")
public class ReservationLogController {

    private final ReservationLogDBService service;
    private final ReservationLogMapper mapper;

    @GetMapping
    public ResponseEntity<List<ReservationLogDTO>> getReservations() {
        List<ReservationLog> reservationLogs = service.getAllReservationLogs();
        return ResponseEntity.ok(mapper.mapToReservationLogDtoList(reservationLogs));
    }

    @GetMapping(value = "/{reservationLogId}")
    public ResponseEntity<ReservationLogDTO> getReservationLog(@PathVariable Long reservationLogId) throws ReservationLogNotFoundException {
        return ResponseEntity.ok(mapper.mapToReservationLogDTO(
                service.getReservationLog(reservationLogId)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createReservationLog(@RequestBody ReservationLogDTO reservationLogDTO) {
        ReservationLog reservation = mapper.mapToReservationLog(reservationLogDTO);
        service.saveReservationLog(reservation);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{reservationLogId}")
    public ResponseEntity<Void> deleteReservationLog(@PathVariable Long reservationLogId) throws ReservationLogNotFoundException {
        service.deleteReservationLog(reservationLogId);
        return ResponseEntity.ok().build();
    }

}
