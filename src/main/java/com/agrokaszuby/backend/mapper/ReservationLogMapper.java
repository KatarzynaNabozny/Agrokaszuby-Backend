package com.agrokaszuby.backend.mapper;

import com.agrokaszuby.backend.domain.ReservationLog;
import com.agrokaszuby.backend.domain.dto.ReservationLogDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationLogMapper {

    public ReservationLog mapToReservationLog(final ReservationLogDTO reservationLogDTO) {
        return new ReservationLog(
                reservationLogDTO.getReservationLogId(),
                reservationLogDTO.getEmail(),
                reservationLogDTO.getEvent(),
                reservationLogDTO.getSuccessful(),
                reservationLogDTO.getDate()
        );
    }

    public ReservationLogDTO mapToReservationLogDTO(final ReservationLog reservationLog) {
        return new ReservationLogDTO(
                reservationLog.getReservationLogId(),
                reservationLog.getEmail(),
                reservationLog.getEvent(),
                reservationLog.getSuccessful(),
                reservationLog.getDate()
        );
    }

    public List<ReservationLogDTO> mapToReservationLogDtoList(final List<ReservationLog> reservationLogList) {
        return reservationLogList.stream()
                .map(this::mapToReservationLogDTO)
                .collect(Collectors.toList());
    }
}