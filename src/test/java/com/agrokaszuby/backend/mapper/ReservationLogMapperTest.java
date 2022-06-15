package com.agrokaszuby.backend.mapper;

import com.agrokaszuby.backend.domain.ReservationLog;
import com.agrokaszuby.backend.domain.dto.ReservationLogDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ReservationLogMapperTest {

    @InjectMocks
    private ReservationLogMapper reservationLogMapper;

    @Test
    void mapToReservationLog() {
        //Given
        reservationLogMapper = new ReservationLogMapper();
        ReservationLogDTO reservationLogDto = ReservationLogDTO.builder().email("email").build();
        //When
        ReservationLog reservationLog = reservationLogMapper.mapToReservationLog(reservationLogDto);
        //Then
        assertEquals("email", reservationLog.getEmail());

    }

    @Test
    void mapToReservationLogDTO() {
        //Given
        reservationLogMapper = new ReservationLogMapper();
        ReservationLog reservationLog = new ReservationLog();
        //When
        ReservationLogDTO reservationLogDto = reservationLogMapper.mapToReservationLogDTO(reservationLog);
        //Then
        assertEquals(null, reservationLogDto.getEmail());

    }

    @Test
    void mapToReservationLogDTOList() {
        //Given
        reservationLogMapper = new ReservationLogMapper();
        ReservationLog reservationLog = new ReservationLog(10L, "email", null, Boolean.TRUE, null);
        List<ReservationLog> reservationLogList = new ArrayList<>();
        reservationLogList.add(reservationLog);
        //When
        List<ReservationLogDTO> reservationLogDto = reservationLogMapper.mapToReservationLogDtoList(reservationLogList);
        //Then
        assertEquals(10, reservationLogDto.get(0).getReservationLogId());
    }
}
